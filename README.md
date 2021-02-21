# API cars

1. API расположен на 
    ```
    http://localhost:8083
    ```
         
1. Эндпоинт, выдающий массив автомобилей в формате JSON.
    ```
    GET /api/cars
    ```
 
    В запросе могут присутствовать следующие параметры:
    1. **country**: если он задан, то выдаются только автомобили, производимые указанной страной.
    1. **segment**: если он задан, то выдаются только автомобили, принадлежащие указанному сегменту.
    1. **minEngineDisplacement**: если он задан, то выдаются только автомобили, у которых рабочий объем двигателя выше или равен указанному значению.
    1. **minEngineHorsepower**: если он задан, то выдаются только автомобили, мощность которых выше или равна указанному значению.
    1. **minMaxSpeed**: если он задан, то выдаются только автомобили, максимальная скорость которых выше или равна указанному значению.
    1. **search**: если он задан, то проводится поиск по следующим полям: по названию модели, поколению и названию модификации.
    1. **isFull**: принимает true/false, если он true, то по автомобилю выдается полная информация: добавляются характеристики двигателя и кузова в отдельные объекты **engine** и **body**.
    1. **year**: если он задан, то выдаются только автомобили, которые выпускались в заданный год.
    1. **bodyStyle**: если он задан, то выдаются только автомобили, у которых есть этот тип кузова.
    
   Пример запроса:
    ```
    http://localhost:8083/api/cars?country=Germany&segment=E-segment&minEngineDisplacement=4.0&minEngineHorsepower=250&minMaxSpeed=200&search=5&isFull=true
    ```
   Пример ответа:
   ```
    [
        {
            "id": 137,
            "segment": "E-segment",
            "country": "Germany",
            "brand": "Audi",
            "model": "A6",
            "generation": "C5",
            "modification": "4.2 quattro",
            "engine": {
                "engine_type": "GASOLINE",
                "engine_cylinders": "V8",
                "engine_displacement": 4172,
                "engine_horsepower": 300
            },
            "body": {
                "body_length": 4796,
                "body_width": 1810,
                "body_height": 1452,
                "body_style": "Sedan,  Wagon"
            }
        },
        ...
    ]
   ```
    
1. Эндпоинты со следующими словарями:
   * всех возможных типов топлива
   ```
   GET /api/fuel-types
   ```
   * всех возможных типов кузова
   ```
   GET /api/body-styles
   ```
   * всех возможных типов двигателя 
   ```
   GET /api/engine-types
   ```
   * всех возможных типов привода 
   ```
   GET /api/wheel-drives
   ```
   * всех возможных типов коробок передач 
   ```
   GET /api/gearboxes
   ```   
1. Эндпоинт, выдающий  значение **средней максимальной скорости** по бренду или модели (параметры в запросе **brand**/**model**).
   ```
   GET /api/max-speed
   ```
   Пример запроса:
   ```
   http://localhost:8083/api/max-speed?brand=BMW
   ```