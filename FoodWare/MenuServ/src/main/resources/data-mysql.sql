INSERT INTO cat_group (name) VALUES
('Food'),
('Drinks'),
('Specialties');

INSERT INTO category (name,group_id) VALUES
('Pizza','1'),
('Pasta','1'),
('Soft Drinks','2'),
('Tea','2'),
('Coffee','2'),
('Sauces','3');

INSERT INTO product (name,price,description,category_id) VALUES
('Quattro Formagi','20','4 types of cheese','1'),
('Diavola','20','really spicy','1'),
('Tonno & Cipolla ','20','tuna and onions','1'),
('Quattro Stagioni','20','mushrooms and olives','1'),
('Primavera','15','contains meat','2'),
('Bolognese','15','contains meat','2'),
('Coca-Cola','10','sugar free','3'),
('Sprite','10','tastes like lemon','3'),
('Fanta','10','orange','3'),
('Prigat','10','lemonade taste','3'),
('Fuzetea','10','raspberry taste','3'),
('Chamomile','10','tea with a slice of lemon','4'),
('Mint','10','strong mint','4'),
('Green','10','tea with a slice of lemon','4'),
('Black','10','tea with a slice of lemon','4'),
('Cappuccino','15','contains milk','5'),
('Black','10','does not contain milk','5'),
('Latte','15','contains milk','5'),
('Espresso','12','really strong','5'),
('Irish','20','contains whiskey and cream','5');

INSERT INTO extra (name,price,category_id) VALUES
('Garlic Sauce','5',1),
('Tomato Sauce','5',1),
('Hot Sauce','5',1);
