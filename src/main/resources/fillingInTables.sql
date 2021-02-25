insert into Client (Sirname, [Name], PhoneNumber, Discount) values
('Cruse', 'Tom', '89011112233', 0),
('Spilberg', 'Stephen', '89011332233', 0),
('Hiddlestion', 'Tom', '89911332233', 2),
('Пупкин','Вася','88005553535', 3)

insert into Category (CategoryName) values ('mains'), ('starters'), ('desserts'), ('drinks')

insert into Unit (UnitName) values ('gram'), ('milliliter'), ('piece')

insert into Dish ([Name], [CategoryID], [Price], [Weight], [UnitID]) values
('Summer Salad', 2, 380, 300, 1),
('Hawaiian Mini Pizza', 2, 290, 290, 1),
('Chilli Crab Mini Pizza', 2, 340, 300, 1),
('Beef Burger', 1, 720, 590, 1),
('Feesh & Chips', 1, 530, 450, 1),
('Spaghetti with Prawns', 1, 600, 390, 1),
('Waffle', 3, 210, 90, 1),
('Ice Cream', 3, 90, 70, 1),
('Espresso', 4, 230, 200, 2),
('Double Espresso', 4, 430, 400, 2),
('Latte', 4, 280, 350, 2)