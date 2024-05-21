# Intcomex Integraciones

Base de datos

* Postgresql
* Cloud SQL Studio

https://intcomexintegraciones.uc.r.appspot.com

Crear Categoría (  POST    /Category/) 
https://intcomexintegraciones.uc.r.appspot.com/categories/

{
    "categoryName": "CLOUD",
    "description": "Servidores de CLOUD",
    "picture": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABrElEQVR42mNgoDZ4l0gUqgcF3oGi/+9uwzJy3nlkJSEmMzNzMzY3t7u/4/Bgw8TAwcEAxS/f39NLS0tRUVFZWVlSUlKUlJSVlZWVFRUVFVVVVWVlZWUlJS0tLSlJSUlp6enpSUlJPT09JSUlRUVFRUVFVVVVVVVVVhYWFhYWFRUVFRUVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"
}
{
    "categoryName": "Servidores",
    "description": "Servidores de Servidores",
    "picture": "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABrElEQVR42mNgoDZ4l0gUqgcF3oGi/+9uwzJy3nlkJSEmMzNzMzY3t7u/4/Bgw8TAwcEAxS/f39NLS0tRUVFZWVlSUlKUlJSVlZWVFRUVFVVVVWVlZWUlJS0tLSlJSUlp6enpSUlJPT09JSUlRUVFRUVFVVVVVVVVVhYWFhYWFRUVFRUVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"
}

# Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/crearCategoriaExitoso.PNG?raw=true)

# Categoria existente
![Exitoso](https://github.com/javf1016/Images/blob/main/crearCategoriaExistente.PNG?raw=true)

Crear Producto (  POST    /Product/)
https://intcomexintegraciones.uc.r.appspot.com/products/

{
    "productName": "Producto de Ejemplo",
    "supplierId": 1,
    "category": {
        "categoryId": 1
    },
    "quantityPerUnit": "1 unidad",
    "unitPrice": 10.99,
    "unitsInStock": 100,
    "unitsOnOrder": 20,
    "reorderLevel": 10,
    "discontinued": false
}

https://intcomexintegraciones.uc.r.appspot.com/products/bulk genera 1000000 registros

# Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/crearproductoExitoso.PNG?raw=true)

# Categoria existente
![Exitoso](https://github.com/javf1016/Images/blob/main/crearProductoyaExistente.PNG?raw=true)

listar productos ( GET	/Products/)
https://intcomexintegraciones.uc.r.appspot.com/products/

# Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/listarProductos.PNG?raw=true)

listar productos por ID (GET    /Products/id/)
https://intcomexintegraciones.uc.r.appspot.com/products/1

# Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/ListarId.PNG?raw=true)

