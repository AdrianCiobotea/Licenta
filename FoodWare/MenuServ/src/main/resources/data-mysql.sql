INSERT INTO cat_group (name) VALUES ('Food'), ('Drinks'), ('Specialties');

INSERT INTO category (name,group_id) VALUES ('Pizza','1'), ('Pasta','1'), ('Soft Drinks','2'),('Tea','2'), ('Coffee','2'), ('Sauces','3');

INSERT INTO product (name,price,description,category_id,is_extra) VALUES
('Quattro Formagi','20','4 types of cheese','1',0),
('Diavola','20','really spicy','1',0),
('Tonno & Cipolla ','20','tuna and onions','1',0),
('Quattro Stagioni','20','mushrooms and olives','1',0),
('Garlic Sauce','5','tastes like garlic','6',1),
('Tomato Sauce','5','sweet','6',1),
('Hot Sauce','5','tomato but spicy','6',1),
('Primavera','15','contains meat','2',0),
('Bolognese','15','contains meat','2',0),
('Coca-Cola','10','sugar free','3',0),
('Sprite','10','tastes like lemon','3',0),
('Fanta','10','orange','3',0),
('Prigat','10','lemonade taste','3',0),
('Fuzetea','10','raspberry taste','3',0),
('Chamomile','10','tea with a slice of lemon','4',0),
('Mint','10','strong mint','4',0),
('Green','10','tea with a slice of lemon','4',0),
('Black','10','tea with a slice of lemon','4',0),
('Cappuccino','15','contains milk','5',0),
('Black','10','does not contain milk','5',0),
('Latte','15','contains milk','5',0),
('Espresso','12','really strong','5',0),
('Irish','20','contains whiskey and cream','5',0);
