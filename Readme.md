##Тестовое задание по Java:
Спроектировать и реализовать REST сервис на Java. Сервис должен получать данные с сервиса OSM (Open Street Map) который возвращает географические данные (множество координатных точек описывающих географическое положение объекта) какого-либо субъекта Российской Федерации. Класс должен возвращать массив координат наибольшей части гео-объекта по его названию и типу (например название “Самарская область”, тип “region”), а также вычислять положение географического центра полученного массива координат. Данные должны кэшироваться во избежание повторных запросов к OSM сервису. Пример URL запроса для сервиса OSM: <br/>http://nominatim.openstreetmap.org/search?state=Самарская область&country=russia&format=json&polygon_geojson=1 - Для областей <br/>http://nominatim.openstreetmap.org/search?q=ПФО&country=russia&format=json&polygon_geojson=1 - Для Фед. округов

####Руководство для пользования
Для запуска приложения необходимо иметь установленную версию Java не ниже 8 версии.<br/>
Для получения ответа запустите исполняемый файл командой ``java -jar <полный адресс до исполняемого файла>``, введите в адресной строке браузера соответствующий запрос. Например :

``
http://localhost:8080/search?state=<STATE>
``
или 
``
http://localhost:8080/search?county=<COUNTY>
``
<br/>Где STATE - любая из областей РФ, COUNTY - федеральный округ.

Пример запроса и ответа:

```
http://localhost:8080/search?state=Самарская область
```

```
[{"boundingbox":["51.7730366","54.6771616","47.9304408","52.568276"],"lat":"53.2128813","lon":"50.8914633","display_name":"Самарская область, Приволжский федеральный округ, Россия"}]
```