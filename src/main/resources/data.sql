

--- INSERT ROLES
INSERT INTO ROLES(ID, NAME) VALUES('81e47e1c-a566-11ec-b909-0242ac120002', 'USER');
INSERT INTO ROLES(ID, NAME) VALUES('b60974bc-7fe1-44c9-a874-cd989b961ec9', 'ADMIN');


--- INSERT PRODUCTS
INSERT INTO PRODUCTS(ID, NAME, DESCRIPTION, SKU) VALUES ('d2cae6f5-736f-4ae1-addc-99e26a5426e3','Camiseta', 'Camiseta branca masculina', 'C090-200-000');
INSERT INTO PRODUCTS(ID, NAME, DESCRIPTION, SKU) VALUES ('3d5b5c38-9995-4d55-8167-92b0d6a37eca','Calça', 'Calça skinny feminina', 'C887-100-080');
INSERT INTO PRODUCTS(ID, NAME, DESCRIPTION, SKU) VALUES ('34d71e45-54fc-45ce-a783-be39f8bf7982', 'Vestido', 'Vestido longo rosa', 'V192-877-447');

-- USUARIOS VIA API

{
    "name":"",
    "username":"",
    "password":"",
    "address":"",
    "dateofbirth":""
}


-- ROLES VIA API
{
    "idUser":"",
    "idsRoles":[
        ""
    ]
}