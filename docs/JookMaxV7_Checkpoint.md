# JookMax V7 — چک‌پوینت وضعیت پروژه
**آخرین به‌روزرسانی:** 2026-09-07
**این فایل باید بعد از پایان هر فاز، دستی آپدیت شود** (طبق «معیار پایان فاز» در سند `JookMaxV7_Complete_Development_Master_Plan.md`)

نحوه‌ی خواندن این فایل:
- ✅ = کامل و تأیید شده
- ⚠️ = ناقص / اسکلت است ولی منطق واقعی ندارد
- ❌ = هنوز شروع نشده
- 🔲 = چک‌باکس باز برای وقتی که آن مورد را انجام دادی

---

## وضعیت کلی Build

- Build فعلی: ✅ `BUILD SUCCESSFUL`
- Stack: Kotlin 2.2.0 · AGP 8.11.1 · Compose BOM 2025.06.00 · Hilt 2.57.1 · minSdk 26 / target-compile 36 · JVM 21
- معماری فعلی: تک‌ماژولی (Package-by-Layer داخل `:app`) — تفکیک به ماژول‌های Gradid واقعی طبق برنامه تا فاز ۱۴ به تعویق افتاده

---

## فاز ۰ — رفع بدهی فنی
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 ۰.۱ افزودن `JookMaxApplication` با `@HiltAndroidApp` + `@AndroidEntryPoint` روی `MainActivity`
- 🔲 ۰.۲ حذف پوشه‌ی فیزیکی `core/` مرده (خارج از `app`، include‌نشده در Gradle)
- 🔲 ۰.۳ یکدست‌سازی پکیج `core.event` (حذف دوگانگی `core.event` / `core.events`) + اتصال `EngineEvent : AppEvent`
- 🔲 ۰.۴ یکدست‌سازی مسیر/پکیج `EngineManager` (→ `engine.manager`) و `EngineState` (→ `engine.lifecycle`)
- 🔲 ۰.۵ یکی‌سازی مدل‌های تکراری کندل (`Candle` حذف شود، فقط `MarketCandle` بماند)

---

## فاز ۱ — Event-Driven Architecture
**وضعیت کلی فاز: ⚠️ نیمه‌کاره (فقط `EventBus.publish` وجود دارد، مصرف‌کننده ندارد)**

- ✅ `EventBus` (publish پایه)
- ❌ `EventDispatcher`
- ❌ `EventSubscriber` (interface)
- ❌ `MarketEventSubscriber`
- ❌ `EngineEventSubscriber`
- 🔲 اتصال Subscriberها داخل `JookMaxEngine.start()`

---

## فاز ۲ — Persistence (Room)
**وضعیت کلی فاز: ❌ شروع نشده (فعلاً `MarketLocalDataSource` فقط in-memory است)**

- 🔲 افزودن dependency های Room
- 🔲 `JookMaxDatabase`, `MarketPriceEntity`, `MarketCandleEntity`, `MarketDao`
- 🔲 `MarketEntityMapper`
- 🔲 بازنویسی `MarketLocalDataSource` با Room واقعی

---

## فاز ۳ — Logging
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 `Logger` (interface) + `JookMaxLogger`
- 🔲 `LogEntry`
- 🔲 اتصال Logger به `EngineLifecycleManager`, `BrainManager`, Subscriberهای فاز ۱

---

## فاز ۴ — Monitoring
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 `PerformanceMonitor`, `PerformanceSnapshot`
- 🔲 `HealthCheck`

---

## فاز ۵ — Analytics
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 `DecisionAnalytics`, `DecisionRecord`
- 🔲 `DecisionEntity` + DAO
- 🔲 اتصال به خروجی `DecisionEngine` از طریق یک `DecisionEvent` جدید

---

## فاز ۶ — WebSocket Market Feed (داده‌ی زنده‌ی XAU/USD)
**وضعیت کلی فاز: ❌ شروع نشده**

⚠️ **پیش‌نیاز تصمیم کاربر (هنوز مشخص نشده):** انتخاب Provider داده‌ی زنده + آیا اپ نیاز به اجرای Background دائمی (Foreground Service) دارد یا نه.

- 🔲 `MarketSocketClient`, `MarketSocketListener`, `SocketConnectionState`, `ReconnectStrategy`
- 🔲 `observeLivePrice(): Flow<MarketPrice>` در `MarketRepository`/`MarketRemoteDataSource`

---

## فاز ۷ — Tick Engine
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 `TickProcessor`, `CandleBuilder`, `TickBuffer`
- 🔲 اتصال به `observeLivePrice()` فاز ۶

---

## فاز ۸ — تحلیل تکنیکال واقعی
**وضعیت کلی فاز: ⚠️ فقط اسکلت (`MarketBrain.analyze()` مقدار ثابت `status/confidence` برمی‌گرداند، منطق واقعی ندارد)**

- ❌ `MovingAverage`, `RSI`, `MACD`, `ATR`
- ❌ `TechnicalAnalyzer`, `MarketCondition`
- 🔲 بازنویسی `MarketBrain.analyze()` با داده‌ی واقعی

---

## فاز ۹ — مدیریت ریسک واقعی
**وضعیت کلی فاز: ⚠️ فقط اسکلت (`RiskBrain` بر اساس یک عدد ورودی دستی `marketVolatility` کار می‌کند)**

⚠️ **پیش‌نیاز تصمیم کاربر (هنوز مشخص نشده):** پروفایل ریسک پیش‌فرض (Conservative/Moderate/Aggressive)

- ❌ `PositionSizer`, `StopLossCalculator`, `RiskProfile`
- 🔲 بازنویسی `RiskBrain.evaluateRisk()` با ATR واقعی (فاز ۸)

---

## فاز ۱۰ — Backtesting Engine
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 `BacktestRunner`, `BacktestResult`, `HistoricalDataLoader`

---

## فاز ۱۱ — AI Learning Layer
**وضعیت کلی فاز: ⚠️ فقط اسکلت (`LearningBrain` فقط شمارنده‌ی ساده دارد، یادگیری واقعی صفر است)**

⚠️ **پیش‌نیاز تصمیم کاربر (هنوز مشخص نشده):** سطح پیچیدگی یادگیری (امتیازدهی ساده در برابر مدل ML واقعی)

- ❌ `TrainingSample`, `RewardCalculator`, `LearningStrategy`
- 🔲 بازنویسی `LearningBrain.learn()` با ذخیره‌ی وزن واقعی روی دیسک

---

## فاز ۱۲ — هوش معاملاتی نهایی
**وضعیت کلی فاز: ⚠️ فقط اسکلت (`DecisionEngine` با threshold ثابت `>= 0.7` / `<= 0.3` کار می‌کند، `BrainManager.process()` بدنه‌اش خالی است)**

- 🔲 بازنویسی `DecisionEngine.decide()` با وزن‌های یادگرفته‌شده + `MarketCondition`
- 🔲 پیاده‌سازی کامل پایپ‌لاین در `BrainManager.process()`

---

## فاز ۱۳ — UI/UX کامل
**وضعیت کلی فاز: ❌ شروع نشده (فقط قالب پیش‌فرض `Greeting("Android")`)**

- ❌ `DashboardScreen` + `DashboardViewModel`
- ❌ `PriceChartScreen`
- ❌ `RiskSettingsScreen`, `BacktestScreen`
- ❌ `JookMaxNavHost`

---

## فاز ۱۴ — Testing & Hardening
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 Unit Test برای `brain/*`, `TickProcessor`/`CandleBuilder`
- 🔲 Instrumented Test پایه برای UI
- 🔲 تصمیم نهایی درباره‌ی تفکیک ماژول‌های Gradle واقعی
- 🔲 بررسی مصرف باتری/CPU در اجرای طولانی‌مدت
- 🔲 بررسی امنیتی عدم وجود کلید API در کد Commit‌شده

---

## فاز ۱۵ — اعتبارسنجی نهایی
**وضعیت کلی فاز: ❌ شروع نشده**

- 🔲 اجرای پیوسته‌ی چند ساعته بدون کرش/نشتی حافظه
- 🔲 نسخه‌ی Release واقعی با `isMinifyEnabled = true` و بررسی ProGuard
- 🔲 بک‌آپ نهایی «Baseline رسمی نسخه‌ی ۱»

---

## خلاصه‌ی نقطه‌ی فعلی (این خط را بعد از هر فاز آپدیت کن)

> **آخرین فاز تکمیل‌شده: هیچ‌کدام — پروژه در پایان «پیش از فاز ۰» است.**
> **قدم بعدی: شروع فاز ۰ (رفع بدهی فنی)، به‌خصوص ۰.۱ فعال‌سازی Hilt، چون هیچ فاز دیگری بدون آن قابل تست واقعی نیست.**
