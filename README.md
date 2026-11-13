# Модуль 2 — практические задания 1–10

Android-приложение на Kotlin и Jetpack Compose с решениями практических заданий 1–10 модуля 2.

| Задание | Файл |
|---|---|
| 1–2. Функция `Hello(name)` и макеты предпросмотра | `app/src/main/java/com/example/module2/tasks/Task01Hello.kt` |
| 3. Оформление текста | `tasks/Task03TextStyles.kt` |
| 4. Кнопка «Нажми на меня» | `tasks/Task04Button.kt` |
| 5. Контейнер с инициалами | `tasks/Task05Initials.kt` |
| 6. Векторное изображение круга | `tasks/Task06Images.kt`, `res/drawable/circle.xml` |
| 7. Контакты на Column и Row | `tasks/Task07Contacts.kt` |
| 8. Трекер воды | `tasks/Task08WaterTracker.kt` |
| 9. UI-тесты трекера воды | `app/src/androidTest/java/com/example/module2/WaterTrackerUiTest.kt` |
| 10. Корзина онлайн-магазина | `tasks/Task10ShoppingCart.kt` |

При запуске открывается меню заданий. Экран задания можно открыть сразу:

```bash
adb shell am start -n com.example.module2/.MainActivity --ei task 8
```

## Сборка и тесты

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest            # локальные тесты
./gradlew connectedDebugAndroidTest    # UI-тесты, нужен эмулятор или устройство
```

Стек: Kotlin 2.2.10, Android Gradle Plugin 9.2.1, Compose BOM 2026.02.01, Material 3, minSdk 26, targetSdk 36.
