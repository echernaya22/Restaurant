insert into Client (Sirname, [Name], PhoneNumber, Discount) values
('First', 'Client', '89011112233', 0),
('Second', 'Client', '89011332233', 0),
('Third', 'Client', '89911332233', 2),
('Пупкин','Вася','88005553535', 3)

insert into Category (CategoryName) values ('mains'), ('starters'), ('desserts'), ('drinks')

insert into Unit (UnitName) values ('gram'), ('milliliter'), ('piece')

insert into Dish ([Name], [CategoryID], [Price], [Weight], [UnitID]) values
('Summer Salad', 2, 380, 300, 1),
('Hawaiian Mini Pizza', 2, 290, 290, 1),
('Chilli Crab Mini Pizza', 2, 340, 300, 1),
('Beef Burger', 1, 720, 590, 1),
('Fish & Chips', 1, 530, 450, 1)