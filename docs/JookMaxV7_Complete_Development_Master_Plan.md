# سند مرجع نهایی و واحد — نقشه‌راه کامل ساخت JookMax V7
**از وضعیت فعلی پروژه تا نسخه‌ی نهایی و کامل اپ**
**نسخه: رسمی/واحد — جایگزین تمام اسناد پراکنده‌ی قبلی**
**مبنا:** بررسی مستقیم کد واقعی پروژه (زیپ ارسالی) + سند Checkpoint داخلی خود پروژه
**منشأ پروژه:** ساخته‌شده از صفر مطلق، بدون Backup — این سند از همین نقطه به بعد را برنامه‌ریزی می‌کند.

---

## بخش صفر — نحوه‌ی استفاده از این سند

این سند یک **نقشه‌راه فاز‌به‌فاز** است. هر فاز دارای این ساختار ثابت است:

- **هدف فاز**
- **پیش‌نیاز (فازهای قبلی که باید تمام شده باشند)**
- **فایل‌ها/کلاس‌های جدیدی که باید ساخته شوند** (با مسیر پکیج دقیق)
- **فایل‌های موجودی که باید تغییر کنند**
- **قوانین مخصوص این فاز**
- **معیار پایان فاز (Definition of Done)**

قانون اصلی حرکت بین فازها: **هیچ فاز جدیدی شروع نشود مگر اینکه فاز قبلی «Definition of Done» خودش را کامل رد کرده و Build سبز باشد.** فازها را نمی‌توان جابه‌جا یا موازی اجرا کرد مگر جایی که صریحاً گفته شده باشد.

---

## بخش الف — قوانین طلایی دائمی پروژه (در تمام فازها معتبر)

1. **جهت وابستگی همیشه یک‌طرفه است:**
   `ui → domain ← data`
   `brain → domain (interface) نه data (impl)`
   `engine → brain, core, domain` (نه بالعکس)
   هیچ‌وقت `domain` به Android SDK، Retrofit، Room، یا Compose وابسته نشود.

2. **یک مدل رسمی برای هر مفهوم.** در حال حاضر `Candle`, `MarketCandle`, `MarketTick` هم‌پوشانی دارند — قبل از فاز ۱ باید یکی‌سازی شوند (جزئیات در فاز ۰).

3. **مسیر فیزیکی فایل = پکیج فایل.** هر فایلی که در پوشه‌ی X است باید دقیقاً پکیج X را داشته باشد؛ استثنا ندارد.

4. **Build-Before-Progress:** پایان هر فاز = `./gradlew build` سبز، بدون Warning جدید نسبت به فاز قبل.

5. **بدون Hack موقت.** اگر چیزی placeholder است (مثل `LearningBrain` فعلی)، باید به‌صراحت با کامنت `// TODO(PhaseX):` علامت‌گذاری شود، نه اینکه به‌عنوان پیاده‌سازی نهایی جا بزند.

6. **بدون تکرار Repository/Model/Hilt Binding.** قبل از ساختن هر کلاس جدید، جست‌وجو شود که آیا معادلش از قبل وجود دارد.

7. **این اپ فقط برای استفاده‌ی شخصی است** — نیازی به رعایت الزامات انتشار در Google Play (مثل سیاست‌های تبلیغاتی یا محدودیت‌های خاص) نیست، ولی استانداردهای امنیتی پایه (کلید API در کد hardcode نشود، از `local.properties` یا `BuildConfig` استفاده شود) باید رعایت شود.

8. **ماژول‌بندی فیزیکی Gradle زودهنگام انجام نشود.** تا پایان فاز ۶ (Real-time Feed)، معماری تک‌ماژولی (Package-by-Layer داخل `:app`) حفظ می‌شود. تفکیک به ماژول‌های Gradle واقعی (اگر لازم شد) فقط در فاز ۱۴ (Hardening) بررسی می‌شود.

---

## بخش ب — معماری نهایی هدف (تصویر پایان مسیر)

```
com.jookmax.v7
├── core/
│   ├── model/        (مدل‌های یکی‌شده: MarketPrice, MarketCandle, TimeFrame, Symbol)
│   ├── event/         (یکدست‌شده — همه core.event)
│   ├── time/
│   └── logging/       (فاز ۳)
├── data/
│   ├── local/          (Room DB — فاز ۲)
│   ├── remote/
│   │   ├── rest/
│   │   └── socket/     (WebSocket — فاز ۶)
│   ├── mapper/
│   └── repository/
├── domain/
│   ├── repository/
│   └── usecase/
├── engine/
│   ├── lifecycle/
│   ├── runtime/
│   ├── tick/           (فاز ۷)
│   └── manager/
├── brain/
│   ├── market/         (تحلیل تکنیکال واقعی — فاز ۸)
│   ├── risk/           (مدیریت ریسک واقعی — فاز ۹)
│   ├── learning/        (AI Learning — فاز ۱۱)
│   ├── decision/         (Trading Intelligence — فاز ۱۲)
│   └── backtest/        (فاز ۱۰)
├── monitoring/          (فاز ۴)
├── analytics/           (فاز ۵)
├── di/
└── ui/
    ├── dashboard/
    ├── chart/
    ├── settings/
    └── theme/
```

---

## فاز ۰ — رفع بدهی فنی موجود (پیش‌نیاز اجباری همه‌ی فازهای بعدی)

### هدف
رفع ۵ مشکل ساختاری کشف‌شده در بررسی کد، قبل از افزودن هر قابلیت جدید.

### اقدامات دقیق

**۰.۱ فعال‌سازی واقعی Hilt**
- ساخت `app/src/main/java/com/jookmax/v7/JookMaxApplication.kt`:
  ```kotlin
  @HiltAndroidApp
  class JookMaxApplication : Application()
  ```
- افزودن `android:name=".JookMaxApplication"` به `<application>` در `AndroidManifest.xml`
- افزودن `@AndroidEntryPoint` روی `MainActivity`
- تزریق `EngineManager` در `MainActivity` با `@Inject lateinit var` به‌جای ساخت دستی، برای تست واقعی زنجیره‌ی DI

**۰.۲ تصمیم درباره‌ی ماژول `core` مرده**
- **تصمیم پیشنهادی: حذف کامل پوشه‌ی فیزیکی `core/` از ریشه‌ی پروژه** (چون محتوایش تکراری و ناقص‌تر از نسخه‌ی داخل `app` است) و تمرکز صرفاً روی `app/.../core/`.

**۰.۳ یکدست‌سازی پکیج `core.event`**
- تغییر پکیج تمام فایل‌های داخل `app/.../core/events/` به یک پکیج واحد: `com.jookmax.v7.core.event` (مفرد، طبق چیزی که `AppEvent` از قبل دارد)
- تغییر نام پوشه از `events` به `event` تا با پکیج یکی شود
- اتصال واقعی `AppEvent` به سلسله‌مراتب: `EngineEvent` باید `AppEvent` را extend/implement کند تا orphan نباشد:
  ```kotlin
  sealed interface EngineEvent : AppEvent
  ```
  و برای این کار `MarketEvent`/`SystemEvent` باید `type` و `metadata` را هم فراهم کنند.

**۰.۴ یکدست‌سازی مسیر/پکیج در `engine`**
- انتقال `EngineManager.kt` به پوشه‌ی `engine/manager/` تا با پکیج `engine.manager` هماهنگ شود، **یا** تغییر پکیجش به `com.jookmax.v7.engine` — تصمیم پیشنهادی: انتقال فایل به `engine/manager/EngineManager.kt` (چون اسمش «Manager» است و باید مثل بقیه‌ی زیرسیستم‌ها زیرپوشه داشته باشد)
- تغییر پکیج `EngineState.kt` از `com.jookmax.v7.engine` به `com.jookmax.v7.engine.lifecycle` تا با مسیر فیزیکی‌اش یکی شود

**۰.۵ یکی‌سازی مدل‌های تکراری**
- بررسی سه مدل `Candle`, `MarketCandle`, `MarketTick`:
  - `MarketTick` → نگه داشته می‌شود (برای داده‌ی لحظه‌ای/real-time، فاز ۷ به آن نیاز دارد)
  - `Candle` و `MarketCandle` → یکی‌سازی به یک مدل واحد به نام `MarketCandle` (چون مستندتر است)؛ تمام ارجاعات `Candle` (مثل `MarketEvent.CandleClosed`) به `MarketCandle` تغییر کند
- حذف فایل `Candle.kt`

### معیار پایان فاز ۰
- [ ] اپ روی دستگاه/امولاتور اجرا می‌شود و Hilt بدون کرش کار می‌کند (لاگ `JookMaxEngine` قابل مشاهده باشد)
- [ ] پوشه‌ی `core/` ریشه دیگر وجود ندارد
- [ ] تمام فایل‌های `core/event/` پکیج یکسان دارند و `AppEvent` واقعاً استفاده می‌شود
- [ ] هیچ فایلی پکیج ناسازگار با مسیرش ندارد (`engine.manager`, `engine.lifecycle` هردو درست)
- [ ] فقط یک مدل کندل (`MarketCandle`) در کل پروژه وجود دارد
- [ ] `./gradlew build` سبز

---

## فاز ۱ — تکمیل معماری Event-Driven (طبق Checkpoint داخلی پروژه)

### هدف
اتصال واقعی EventBus به Brain/Engine تا رویدادها واقعاً منتشر و مصرف شوند.

### فایل‌های جدید
- `core/event/EventDispatcher.kt` — لایه‌ی بالای `EventBus`، مسئول فیلتر کردن رویدادها بر اساس `EventType` و ارسال به Subscriberهای ثبت‌شده
- `core/event/EventSubscriber.kt` (interface):
  ```kotlin
  interface EventSubscriber<T : AppEvent> {
      suspend fun onEvent(event: T)
  }
  ```
- `brain/market/MarketEventSubscriber.kt` — گوش‌دادن به `MarketEvent.PriceUpdated` و صدا زدن `MarketBrain.updateMarket()`
- `engine/EngineEventSubscriber.kt` — گوش‌دادن به `SystemEvent` برای لاگ کردن شروع/توقف Engine (پایه برای فاز ۳)

### فایل‌های تغییریافته
- `JookMaxEngine.kt` — به‌جای `publishEvent` تنها، در `start()` باید Subscriberها را به `EventDispatcher` وصل کند (collect کردن `eventBus.events` در `EngineCoroutineScope`)
- `di/CoreModule.kt` — افزودن `provideEventDispatcher()`

### قوانین فاز
- هیچ Subscriber نباید مستقیم `MutableSharedFlow` را ببیند؛ فقط از طریق `EventBus.events` (public read-only) کار کند — این از قبل رعایت شده، حفظ شود.
- Subscriberها نباید حلقه‌ی رویداد بسازند (subscriber به رویدادی که خودش publish می‌کند گوش ندهد).

### معیار پایان فاز ۱
- [ ] با شبیه‌سازی یک `MarketEvent.PriceUpdated` تستی، `MarketBrain` مقدارش واقعاً آپدیت شود (قابل تست با Unit Test)
- [ ] لاگ ساده (println موقت، قبل از فاز ۳) نشان دهد Engine Start/Stop از طریق EventBus شنیده می‌شود
- [ ] Build سبز

---

## فاز ۲ — لایه‌ی پایداری واقعی (Persistence با Room)

### هدف
جایگزینی `MarketLocalDataSource` in-memory با ذخیره‌ی واقعی روی دیسک.

### وابستگی جدید (در `libs.versions.toml`)
```
room = "2.7.x" (نسخه‌ی پایدار روز ساخت را چک کن)
```
کتابخانه‌ها: `androidx.room:room-runtime`, `androidx.room:room-ktx`, KSP: `androidx.room:room-compiler`

### فایل‌های جدید
- `data/local/db/JookMaxDatabase.kt` (`@Database`)
- `data/local/db/entity/MarketPriceEntity.kt`, `data/local/db/entity/MarketCandleEntity.kt`
- `data/local/db/dao/MarketDao.kt`
- `data/mapper/MarketEntityMapper.kt` (تبدیل Entity ↔ Domain Model، جدا از `MarketMapper` فعلی که DTO↔Domain است)

### فایل‌های تغییریافته
- `MarketLocalDataSource.kt` — تزریق `MarketDao` به‌جای متغیر حافظه؛ متدها `suspend` شوند
- `MarketRepositoryImpl.kt` — هماهنگ با امضای suspend جدید
- `di/DataModule.kt` — افزودن `provideDatabase()`, `provideMarketDao()`

### قوانین فاز
- Entity های Room هرگز نباید مستقیم به `domain` یا `ui` درز کنند — فقط `data.mapper` اجازه‌ی دیدن Entity را دارد (طبق قانون Domain Purity).

### معیار پایان فاز ۲
- [ ] بستن و بازکردن مجدد اپ، آخرین قیمت ذخیره‌شده را نگه دارد
- [ ] `clearCache()` واقعاً جدول را خالی می‌کند
- [ ] Build سبز

---

## فاز ۳ — سیستم Logging

### هدف
جایگزینی هرگونه `println`/کامنت با یک سیستم لاگ ساختاریافته.

### فایل‌های جدید
- `core/logging/Logger.kt` (interface: `debug`, `info`, `warn`, `error`)
- `core/logging/JookMaxLogger.kt` (پیاده‌سازی: در Debug build چاپ به Logcat با تگ `JookMax`، در Release ذخیره در فایل محلی برای دیباگ بعدی چون اپ شخصی است و کاربر گزارش نمی‌فرستد)
- `core/logging/LogEntry.kt` (مدل: timestamp, level, tag, message, throwable?)
- `data/local/db/entity/LogEntity.kt` + DAO مربوطه (اختیاری، برای نگهداری تاریخچه‌ی لاگ روی دیسک)

### فایل‌های تغییریافته
- تمام Subscriberهای فاز ۱ و `EngineLifecycleManager`, `BrainManager` — تزریق `Logger` و ثبت رویدادهای کلیدی (Start/Stop/Error/Decision)
- `di/CoreModule.kt` — `provideLogger()`

### قوانین فاز
- Logger باید Interface باشد تا در تست‌ها بتوان Fake زد.
- هیچ داده‌ی حساس (در آینده اگر API Key اضافه شد) در لاگ چاپ نشود.

### معیار پایان فاز ۳
- [ ] هر Start/Stop Engine، هر تصمیم Brain، و هر خطای شبکه در Logcat با تگ مشخص دیده می‌شود
- [ ] Build سبز

---

## فاز ۴ — Performance Monitoring

### هدف
اندازه‌گیری سلامت اجرای Engine/Brain (نه بورس، بلکه سلامت خود اپ).

### فایل‌های جدید
- `monitoring/PerformanceMonitor.kt` — اندازه‌گیری زمان اجرای هر چرخه‌ی `BrainManager.process()`, نرخ رویدادهای EventBus در ثانیه، مصرف حافظه‌ی تقریبی
- `monitoring/model/PerformanceSnapshot.kt`
- `monitoring/HealthCheck.kt` — بررسی دوره‌ای اینکه آیا Engine در وضعیت `Error` گیر نکرده

### فایل‌های تغییریافته
- `JookMaxEngine.kt` — فراخوانی `PerformanceMonitor` در هر Tick (بعد از فاز ۷ کامل‌تر می‌شود)
- `di/EngineModule.kt` — `providePerformanceMonitor()`

### معیار پایان فاز ۴
- [ ] یک `PerformanceSnapshot` هر N ثانیه در Logger ثبت می‌شود
- [ ] Build سبز

---

## فاز ۵ — Analytics (تحلیل رفتار خود سیستم، نه بازار)

### هدف
جمع‌آوری آمار تصمیمات (چند بار BUY/SELL/HOLD، نرخ موفقیت تخمینی) برای مصرف بعدی توسط AI Learning (فاز ۱۱).

### فایل‌های جدید
- `analytics/DecisionAnalytics.kt` — شمارش و نگهداری تاریخچه‌ی خروجی `DecisionEngine`
- `analytics/model/DecisionRecord.kt`
- `data/local/db/entity/DecisionEntity.kt` + DAO

### فایل‌های تغییریافته
- `brain/decision/DecisionEngine.kt` — بعد از هر `decide()`، نتیجه به `DecisionAnalytics` ارسال شود (از طریق EventBus، نه وابستگی مستقیم — با یک `DecisionEvent` جدید در `core.event`)

### معیار پایان فاز ۵
- [ ] بعد از چند تصمیم شبیه‌سازی‌شده، جدول `DecisionEntity` پر از رکورد واقعی است
- [ ] Build سبز

---

## فاز ۶ — اتصال داده‌ی زنده‌ی بازار (WebSocket Market Feed)

### هدف
جایگزینی Retrofit polling با استریم زنده‌ی قیمت XAU/USD.

### پیش‌نیاز خارجی
انتخاب یک Provider واقعی داده‌ی XAU/USD (مثلاً یک بروکر یا سرویس داده‌ی فارکس) — **این تصمیم باید قبل از این فاز توسط کاربر گرفته شود** چون کلید API و مستندات WebSocket آن Provider لازم است.

### فایل‌های جدید
- `data/remote/socket/MarketSocketClient.kt` (OkHttp WebSocket)
- `data/remote/socket/MarketSocketListener.kt`
- `data/remote/socket/SocketConnectionState.kt` (Connecting/Connected/Disconnected/Reconnecting)
- `data/remote/socket/ReconnectStrategy.kt` (Exponential Backoff)

### فایل‌های تغییریافته
- `MarketRemoteDataSource.kt` — افزودن `observeLivePrice(): Flow<MarketPrice>` در کنار متدهای suspend فعلی (نه جایگزینی کامل Retrofit — Retrofit برای درخواست‌های یک‌باره مثل تاریخچه می‌ماند)
- `MarketRepositoryImpl.kt` — پیاده‌سازی یک متد جدید در `MarketRepository`: `observeLivePrice(): Flow<MarketPrice>`
- `di/NetworkModule.kt` — `provideMarketSocketClient()`

### قوانین فاز
- کلید API هرگز در کد Commit نشود — از `local.properties` + `BuildConfig.API_KEY` استفاده شود.
- Reconnect باید Exponential Backoff داشته باشد (حداکثر تأخیر مشخص، مثلاً ۳۰ ثانیه) تا در قطعی طولانی، درخواست به سرور اسپم نشود.

### معیار پایان فاز ۶
- [ ] با اتصال به سرویس واقعی، `MarketBrain.getLatestMarketPrice()` بدون Polling دستی، به‌روز می‌ماند
- [ ] قطع/وصل شدن اینترنت باعث کرش نمی‌شود و Reconnect خودکار انجام می‌شود
- [ ] Build سبز

**⚠️ نکته:** از این فاز به بعد، هسته‌ی «هوش بازار» شروع می‌شود. فازهای ۷ تا ۱۲ به هم وابسته‌اند و باید دقیقاً به ترتیب انجام شوند چون هرکدام ورودی فاز بعدی را می‌سازد.

---

## فاز ۷ — Tick Engine

### هدف
تبدیل جریان خام Tick (از WebSocket) به کندل‌های OHLC در تایم‌فریم‌های مختلف.

### فایل‌های جدید
- `engine/tick/TickProcessor.kt` — دریافت `MarketTick`، تجمیع در بازه‌ی زمانی هر `TimeFrame`
- `engine/tick/CandleBuilder.kt` — منطق ساخت `MarketCandle` از لیست Tickها
- `engine/tick/TickBuffer.kt` — بافر حافظه‌ای برای Tickهای در حال تجمیع قبل از بسته‌شدن کندل

### فایل‌های تغییریافته
- `JookMaxEngine.kt` — تزریق `TickProcessor`، اتصال آن به `observeLivePrice()` فاز ۶
- `core/event/MarketEvent.kt` — اطمینان از اینکه `CandleClosed` با `MarketCandle` (نه `Candle` حذف‌شده) کار می‌کند (طبق فاز ۰.۵)

### معیار پایان فاز ۷
- [ ] با تزریق چند Tick تستی با تایم‌استمپ مصنوعی، خروجی `CandleBuilder` دقیقاً OHLC درست را می‌دهد (Unit Test)
- [ ] رویداد `MarketEvent.CandleClosed` در پایان هر بازه‌ی زمانی منتشر می‌شود
- [ ] Build سبز

---

## فاز ۸ — موتور تحلیل تکنیکال واقعی (جایگزینی `MarketBrain` Placeholder)

### هدف
تبدیل `MarketBrain` از یک نگهدارنده‌ی ساده به یک تحلیل‌گر واقعی بازار طلا.

### فایل‌های جدید
- `brain/market/indicator/MovingAverage.kt`
- `brain/market/indicator/RSI.kt`
- `brain/market/indicator/MACD.kt`
- `brain/market/indicator/ATR.kt` (برای فاز ۹ ریسک لازم است)
- `brain/market/TechnicalAnalyzer.kt` — ترکیب اندیکاتورها و تولید `MarketAnalysis` واقعی (نه `status = "READY"` فعلی)
- `brain/market/model/MarketCondition.kt` (Trending/Ranging/Volatile)

### فایل‌های تغییریافته
- `brain/market/MarketBrain.kt` — `analyze()` واقعاً از `TechnicalAnalyzer` روی تاریخچه‌ی کندل‌ها (از فاز ۷) استفاده کند به‌جای بازگرداندن مقدار ثابت

### قوانین فاز
- هر اندیکاتور یک کلاس مستقل و قابل تست باشد (ورودی: `List<MarketCandle>`, خروجی: عدد یا سری اعداد) — بدون وابستگی به Engine/EventBus، تا Unit Test مستقیم و ساده باشد.

### معیار پایان فاز ۸
- [ ] با یک سری کندل تستی مشخص (مقادیر دستی)، خروجی هر اندیکاتور با محاسبه‌ی دستی/منبع معتبر مطابقت دارد (Unit Test)
- [ ] `MarketBrain.analyze()` بر اساس داده‌ی واقعی XAU/USD مقدار معنادار برمی‌گرداند
- [ ] Build سبز

---

## فاز ۹ — مدیریت ریسک واقعی (جایگزینی `RiskBrain` Placeholder)

### هدف
تبدیل `RiskBrain` از threshold ثابت به مدیریت ریسک واقعی طلا.

### فایل‌های جدید
- `brain/risk/PositionSizer.kt` (محاسبه‌ی حجم پیشنهادی بر اساس ATR و درصد ریسک قابل‌تنظیم)
- `brain/risk/StopLossCalculator.kt`
- `brain/risk/model/RiskProfile.kt` (تنظیمات ریسک قابل تنظیم توسط کاربر — Conservative/Moderate/Aggressive)

### فایل‌های تغییریافته
- `brain/risk/RiskBrain.kt` — `evaluateRisk()` از `ATR` (فاز ۸) و `RiskProfile` استفاده کند به‌جای یک عدد `marketVolatility` دستی

### معیار پایان فاز ۹
- [ ] تغییر `RiskProfile` واقعاً خروجی `PositionSizer`/`StopLossCalculator` را تغییر می‌دهد (Unit Test)
- [ ] Build سبز

---

## فاز ۱۰ — موتور بک‌تست (Backtesting Engine)

### هدف
اجرای منطق فعلی Brain روی داده‌ی تاریخی، بدون اتصال به بازار زنده، برای سنجش عملکرد قبل از فازهای یادگیری.

### فایل‌های جدید
- `brain/backtest/BacktestRunner.kt` — پخش کندل‌های تاریخی به‌جای WebSocket زنده به همان زنجیره‌ی `TickProcessor → MarketBrain → RiskBrain → DecisionEngine`
- `brain/backtest/BacktestResult.kt` (سود/زیان فرضی، نرخ برد، Drawdown)
- `brain/backtest/HistoricalDataLoader.kt` (خواندن کندل تاریخی از فایل CSV محلی یا از `getMarketHistory()` موجود)

### قوانین فاز
- `BacktestRunner` باید از همان اینترفیس‌های `domain` استفاده کند که Engine زنده استفاده می‌کند (Dependency Inversion) — یعنی هیچ منطق موازی/تکراری برای Brain در حالت بک‌تست نوشته نشود.

### معیار پایان فاز ۱۰
- [ ] اجرای بک‌تست روی حداقل یک بازه‌ی تاریخی مشخص، یک `BacktestResult` قابل‌فهم تولید می‌کند
- [ ] Build سبز

---

## فاز ۱۱ — لایه‌ی یادگیری (AI Learning Layer)

### هدف
جایگزینی `LearningBrain` Placeholder با یادگیری واقعی مبتنی بر نتایج `DecisionAnalytics` (فاز ۵) و `BacktestResult` (فاز ۱۰).

### فایل‌های جدید
- `brain/learning/model/TrainingSample.kt` (ورودی: شرایط بازار در لحظه‌ی تصمیم؛ خروجی: نتیجه‌ی واقعی معامله)
- `brain/learning/RewardCalculator.kt` (تبدیل نتیجه‌ی معامله به Reward عددی)
- `brain/learning/LearningStrategy.kt` (interface — برای اینکه بعداً بتوان الگوریتم را عوض کرد بدون تغییر بقیه‌ی سیستم؛ نسخه‌ی اول می‌تواند یک مدل ساده‌ی امتیازدهی/Weight-based باشد، نه لزوماً Deep Learning کامل)

### فایل‌های تغییریافته
- `brain/learning/LearningBrain.kt` — `learn()` واقعاً `LearningStrategy` را صدا بزند و وزن‌های تصمیم‌گیری را در `data.local` ذخیره کند (به‌جای شمارنده‌ی ساده‌ی فعلی)

### قوانین فاز
- تصمیم درباره‌ی «چقدر پیچیده باشد مدل یادگیری» (امتیازدهی ساده در برابر TensorFlow Lite/مدل واقعی ML) باید صریحاً با کاربر مشخص شود قبل از شروع این فاز — چون پیچیدگی و زمان پیاده‌سازی بسیار متفاوت است.

### معیار پایان فاز ۱۱
- [ ] بعد از اجرای چند دور Backtest، وزن‌های تصمیم‌گیری واقعاً تغییر می‌کنند و این تغییر در دیسک ذخیره می‌ماند
- [ ] Build سبز

---

## فاز ۱۲ — هوش معاملاتی نهایی (Trading Intelligence Brain)

### هدف
جایگزینی threshold‌های ثابت `DecisionEngine` با تصمیم‌گیری ترکیبی از `TechnicalAnalyzer` + `RiskBrain` + `LearningStrategy`.

### فایل‌های تغییریافته
- `brain/decision/DecisionEngine.kt` — بازنویسی کامل `decide()` تا به‌جای threshold ثابت (`>= 0.7`)، از وزن‌های یادگرفته‌شده (فاز ۱۱) و شرایط بازار (`MarketCondition` فاز ۸) استفاده کند
- `brain/BrainManager.kt` — `process()` باید واقعاً پایپ‌لاین کامل را اجرا کند (نه بدنه‌ی خالی فعلی): Market Analysis → Risk Evaluation → Decision → Learning Update

### معیار پایان فاز ۱۲
- [ ] `BrainManager.process()` یک چرخه‌ی کامل و واقعی را بدون هیچ TODO خالی اجرا می‌کند
- [ ] نتایج Backtest نسخه‌ی جدید نسبت به نسخه‌ی threshold ثابت قابل‌مقایسه و قابل‌گزارش است
- [ ] Build سبز

---

## فاز ۱۳ — رابط کاربری کامل (UI/UX Dashboard)

### هدف
ساخت صفحات واقعی به‌جای `Greeting("Android")` پیش‌فرض.

### فایل‌های جدید
- `ui/dashboard/DashboardScreen.kt` + `DashboardViewModel.kt` (قیمت زنده، وضعیت Engine، آخرین تصمیم)
- `ui/chart/PriceChartScreen.kt` (نمایش کندل با یک کتابخانه‌ی چارت Compose)
- `ui/settings/RiskSettingsScreen.kt` (تنظیم `RiskProfile` از فاز ۹)
- `ui/settings/BacktestScreen.kt` (اجرای بک‌تست و نمایش `BacktestResult`)
- `ui/navigation/JookMaxNavHost.kt`

### فایل‌های تغییریافته
- `MainActivity.kt` — جایگزینی کامل بدنه با `JookMaxNavHost`

### قوانین فاز
- ViewModel ها فقط با `domain.usecase` کار کنند، هرگز مستقیم با `data.repository` یا `brain` — طبق قانون جهت وابستگی (بخش الف، بند ۱). برای نمایش وضعیت Engine/Brain، UseCase های جدید در `domain.usecase` ساخته شود (مثلاً `ObserveEngineStateUseCase`).

### معیار پایان فاز ۱۳
- [ ] اپ باز می‌شود و داشبورد زنده قیمت XAU/USD، وضعیت Engine و آخرین تصمیم را نشان می‌دهد
- [ ] صفحه‌ی تنظیمات ریسک و صفحه‌ی بک‌تست کار می‌کنند
- [ ] Build سبز

---

## فاز ۱۴ — سخت‌سازی و تست (Testing & Hardening)

### هدف
پوشش تست و بررسی نهایی معماری قبل از استفاده‌ی واقعی و مداوم.

### اقدامات
- Unit Test برای تمام کلاس‌های `brain/*` (به‌خصوص اندیکاتورها و `DecisionEngine`)
- Unit Test برای `TickProcessor`/`CandleBuilder`
- Instrumented Test پایه برای `DashboardScreen`
- بازبینی این‌که آیا معماری تک‌ماژولی هنوز کافی است یا زمان تفکیک به ماژول‌های Gradle واقعی (`:core`, `:domain`, `:data`, `:brain`, `:engine`, `:ui`) رسیده — این تصمیم فقط اینجا گرفته شود، نه زودتر
- بررسی مصرف باتری/CPU در اجرای طولانی‌مدت Engine (چون اپ قرار است پیوسته اجرا شود)
- بررسی امنیتی: عدم وجود کلید API در کد Commit‌شده (`git log -p | grep -i api_key` یا مشابه)

### معیار پایان فاز ۱۴
- [ ] پوشش تست حداقل روی منطق حیاتی (`brain`, `engine.tick`) وجود دارد
- [ ] تصمیم ماژول‌بندی فیزیکی مستند شده (چه انجام شود چه نشود، با دلیل)
- [ ] Build سبز، بدون Warning مهم

---

## فاز ۱۵ — اعتبارسنجی نهایی و آماده‌سازی نسخه‌ی پایانی

### هدف
آخرین بررسی قبل از اینکه پروژه «کامل» تلقی شود.

### چک‌لیست نهایی
- [ ] تمام ۱۵ فاز بالا Definition of Done خودشان را رد کرده‌اند
- [ ] اپ حداقل یک دوره‌ی طولانی (مثلاً چند ساعت پیوسته) بدون کرش و بدون نشتی حافظه اجرا شده
- [ ] `versionName`/`versionCode` در `app/build.gradle.kts` برای نسخه‌ی پایدار اول به‌روزرسانی شود (مثلاً `1.0` → `1.0.0-stable` یا مشابه، به سلیقه‌ی خودت)
- [ ] یک نسخه‌ی Release واقعی (`isMinifyEnabled = true` با بررسی ProGuard rules برای Room/Retrofit/Hilt) ساخته و روی دستگاه واقعی تست شود
- [ ] بک‌آپ نهایی از کل پروژه (کد + دیتابیس‌های نمونه + این سند) گرفته شود تا از همین‌جا به بعد، اگر تغییر بزرگی لازم شد، این نسخه به‌عنوان «Baseline رسمی نسخه‌ی ۱» ثبت شود

---

## بخش پ — جدول خلاصه‌ی فازها (مرجع سریع)

| فاز | عنوان | خروجی کلیدی |
|---|---|---|
| ۰ | رفع بدهی فنی | Hilt فعال، ساختار پکیج تمیز، مدل یکتا |
| ۱ | Event-Driven Architecture | EventBus واقعاً مصرف می‌شود |
| ۲ | Persistence (Room) | داده روی دیسک ماندگار است |
| ۳ | Logging | ثبت ساختاریافته‌ی رویدادها |
| ۴ | Monitoring | سلامت اجرای خود اپ |
| ۵ | Analytics | آمار تصمیمات برای یادگیری |
| ۶ | WebSocket Feed | داده‌ی زنده‌ی XAU/USD |
| ۷ | Tick Engine | ساخت کندل از Tick |
| ۸ | Technical Analysis | تحلیل واقعی بازار |
| ۹ | Risk Management | مدیریت ریسک واقعی |
| ۱۰ | Backtesting | سنجش عملکرد تاریخی |
| ۱۱ | AI Learning | یادگیری از نتایج |
| ۱۲ | Trading Intelligence | تصمیم‌گیری نهایی هوشمند |
| ۱۳ | UI/UX | داشبورد کامل کاربر |
| ۱۴ | Testing & Hardening | پایداری و پوشش تست |
| ۱۵ | Final Validation | نسخه‌ی پایدار نهایی |

---

## بخش ت — نکات مهمی که باید قبل از ادامه‌ی مسیر توسط خودت (نه Claude) تصمیم‌گیری شود

این‌ها جاهایی هستند که مسیر به انتخاب شخصی تو بستگی دارد و نمی‌توان از قبل در سند قفل کرد:

1. **Provider داده‌ی زنده‌ی XAU/USD** (فاز ۶) — کدام سرویس/بروکر، چه محدودیت نرخ درخواست، چه فرمت WebSocket
2. **سطح پیچیدگی AI Learning** (فاز ۱۱) — امتیازدهی ساده‌ی وزن‌دار در برابر مدل یادگیری ماشین واقعی (TensorFlow Lite و غیره)
3. **سطح ریسک پیش‌فرض** (فاز ۹) — چند درصد سرمایه در هر معامله، پیش‌فرض Conservative/Moderate/Aggressive
4. **آیا نسخه‌ی نهایی نیاز به اجرای Background دائمی دارد** (Foreground Service) یا فقط وقتی اپ باز است کار می‌کند — این روی طراحی فاز ۶ و ۷ تأثیر مستقیم دارد و بهتر است زودتر (قبل از فاز ۶) مشخص شود.
