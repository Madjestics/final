# Проект для финального этапа МегаОлимпиады ИТМО

RESTful API сервис, позволяющий управлять списком кинофильмов и режиссеров с использованием фреймворка Sring Boot и языка Java 

#### Для запуска проекта необходимо установить docker для поднятия контейнеров базы и самого сервиса:

##### Для запуска необходимо прописать в терминале, находясь при этом в папке проекта следующее:
`docker-compose up -d --build`

###### После этого произойдет запуск контейнера с базой, и при корректной работе базы произойдет запуск контейнера самого приложения

##### При этом скрипт для создания таблиц базы лежит в папке: db/init.sql

##### Для доступа к swagger-ui необходимо перейти по ссылке: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). Там можно увидеть контроллер, а также все методы api

## Примеры запросов к api:

#### 1. GET /api/movies - список всех кинофильмов

Возвратить ответ с кодом 200 (OK) и вложить JSON следующей структуры:
````
[
    {
        "id": 1,
        "title": "Example movie",
        "year": 2018,
        "director": {
            "id": 1,
            "fio": "Example exmaple"
        },
        "length": "02:30:00",
        "rating": 8
    },
    ...
]
````

#### 2. GET /api/movies/:id - информация о кинофильме с указанным id

необходимо возвратить ответ с кодом 200 (OK) и вложить JSON следующей структуры:

````
{
    "id": 1,
    "title": "Example movie",
    "year": 2018,
    "director": {
       "id": 1,
       "fio": "Example exmaple"
    },
    "length": "02:30:00",
    "rating": 8
}
````

В случае, если записи с указанным id не найдено, возвращается ответ с кодом 404 (Not Found) и JSON с указанем причины ошибки, пример: 

````
{
    "status": 404,
    "reason": "<Причина неудачи>"
}
````

#### 3. POST /api/movies - добавление новой записи о кинофильме

В теле входящего запроса необходимо ожидать JSON следующей структуры:
````
{
    "id": 1,
    "title": "Example movie",
    "year": 2018,
    "director": {
       "id": 1
    },
    "length": "02:30:00",
    "rating": 8
}
````
Указание ФИО директора при этом необязательно

В случае успешного добавления в список возвращается ответ с кодом 200 (OK) и JSON следующей структуры:
````
{
    "id": 1,
    "title": "Example movie",
    "year": 2018,
    "director": {
       "id": 1,
       "fio": "Example exmaple"
    },
    "length": "02:30:00",
    "rating": 8
}
````
В случае неудачи необходимо возвратить ответ с кодом 500 (Internal Server Error) и вложить JSON следующей структуры:

````
{
    "status": 500,
    "reason": "<Причина неудачи>"
}
````

#### 4. PATCH /api/movies/:id - изменение информации о кинофильме с указанным id

Входящий запрос и формат ответов при удачном и неудачном изменении совпадают с предыдущим методом с POST.

В случае, если записи с указанным id не найдено, возвращается ответ с кодом 404 (Not Found) и JSON следующего вида

````
{
    "status": 404,
    "reason": "<Причина неудачи>"
}
````

#### 5. DELETE /api/movies/:id - удаление записи с указанным id

###### В случае успеха необходимо возвратить ответ с кодом 202 (Accepted).

В случае, если записи с указанным id не найдено, необходимо возвратить ответ с кодом 404 (Not Found) и JSON следующего вида

````
{
    "status": 404,
    "reason": "<Причина неудачи>"
}
````

В случае других ошибок необходимо возвратить ответ с кодом 500 (Internal Server Error) и вложить JSON следующей структуры:
````
{
    "status": 500,
    "reason": "<Причина неудачи>"
}
````
Аналогичные 5 методов реализуются для режиссера по пути запроса `/api/directors`, только в теле входящих запросов передается JSON вида:
````
{
    "id": <уникальный идентификатор записи>,
    "fio": "Example director"
}
````

##### При этом при любом запросе происходит его валидация (либо id если идет поиск записи или удаление, либо валидация полей)

В случае ошибки валидации необходимо сформировать ответ с кодом 400 (Bad Request), в теле ответа указать причину ошибки валидации.

Пример ответа на попытку вставить запись без указания названия кинофильма:
````
{
    "status": 400,
    "reason": "Заголовок кинофильма не может быть пустым"
}
````

Пример ответа на попытку вставить запись с годом, превышающим максимально допустимое значение:
````
{
    "status": 400,
    "reason": "Год выпуска кинофильма должен быть в пределах от 1900 до 2100 годов"
}
````