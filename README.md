<img width="120pt" src="https://cdn.kelcuprum.ru/icons/alinlib.png" align="right">

# AlinLib
Библиотека для облегчения разработки интерфейса, уведомлений и т.д.<br>
Так же содержание в одном дизайн коде
## Интеграция
[![Snapshots](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.kelcuprum.ru%2Fsnapshots%2Fru%2Fkelcuprum%2Falinlib%2FAlinLib%2Fmaven-metadata.xml&label=Snapshots)](https://repo.kelcuprum.ru/#/snapshots/ru/kelcuprum/alinlib/AlinLib)
[![Releases](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.kelcuprum.ru%2Freleases%2Fru%2Fkelcuprum%2Falinlib%2FAlinLib%2Fmaven-metadata.xml&label=Releases)](https://repo.kelcuprum.ru/#/releases/ru/kelcuprum/alinlib/AlinLib)

### Типы
- snapshots [Dev, alpha]
- releases [Releases, Beta, RC]
```groovy
repositories {
    maven {
        name "kelcuprum_TYPE"
        url "https://repo.kelcuprum.ru/TYPE"
    }
}
dependencies {
    modImplementation("ru.kelcuprum.alinlib:AlinLib:VERSION");
}
```
Для указания в fabric.mod.json указывайте ID: `alinlib`
## Скриншоты
### Интерфейс
![interface](https://kelcuprum.ru/ass/alinlib/interface.png?)
### Уведомления
![toast](https://kelcuprum.ru/ass/alinlib/toast.png?)
![custom_toast](https://kelcuprum.ru/ass/alinlib/custom_toast.png?)
