# Car Connect

**Car Connect** — это платформа для аренды автомобилей, которая позволяет пользователям легко бронировать автомобили, оставлять отзывы и загружать фотографии автомобилей. Система поддерживает функции регистрации, аутентификации и управления бронированиями. 

## Стек технологий

- **Backend**: Java, Spring Boot
- **База данных**: PostgreSQL
- **Безопасность**: JWT для авторизации
- **Валидация данных**: Jakarta Validation (Hibernate Validator)
- **Для загрузки изображений**: AWS S3 (для хранения изображений автомобилей)
- **Swagger**: Для документации API

## Установка

Для того чтобы запустить проект на локальной машине, выполните следующие шаги:

1. Клонируйте репозиторий:

    ```bash
    git clone https://github.com/yourusername/car-connect.git
    ```

2. Перейдите в директорию проекта:

    ```bash
    cd car-connect
    ```

3. Скомпилируйте проект:

    ```bash
    ./mvnw clean install
    ```

4. Запустите приложение:

    ```bash
    ./mvnw spring-boot:run
    ```

5. Перейдите по адресу [http://localhost:8080](http://localhost:2020) для доступа к API.

## Документация API

Для подробной документации по использованию API, пожалуйста, посетите [Swagger UI](http://localhost:2020/swagger-ui.html), после того как вы запустите приложение.
И само [Документация](https://docs.google.com/document/d/1gtKqfrvDhnbyhByQiPfcJcWKjGVNmg9VkYi-V_Crou0/edit?usp=sharing)

## Презентация проекта

Презентацию проекта можно скачать по следующей ссылке: [Презентация проекта (PDF)](https://drive.google.com/file/d/yourfileid/view?usp=sharing).

## Демонстрация видео

Посмотрите демонстрацию работы проекта на [YouTube](https://www.youtube.com/watch?v=yourvideoid).

## Структура проекта

Проект разделён на несколько модулей:

- **car_connect** — основной модуль с бизнес-логикой, контроллерами и сервисами.
- **model** — содержит все DTO и модели данных.
- **repository** — слой доступа к данным (реализация взаимодействия с базой данных).
- **service** — содержит бизнес-логику.
- **controller** — контроллеры для обработки HTTP-запросов.

### Пример структуры каталогов:

```plaintext
car-connect
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── example
│   │   │   │   │   ├── car_connect
│   │   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── model
│   │   │   │   │   │   ├── repository
│   │   │   │   │   │   ├── service
│   │   │   │   │   │   └── config
│   ├── resources
│   │   ├── application.properties
│   │   └── static
└── pom.xml
