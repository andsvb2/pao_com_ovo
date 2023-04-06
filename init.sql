CREATE TYPE product_type AS enum(
    'bebida',
    'fruta',
    'verdura',
    'legume',
    'higiene',
    'limpeza',
    'padaria',
    'outros'
    );

CREATE TYPE measurement_unit AS enum(
    'mililitro',
    'litro',
    'grama',
    'quilograma',
    'unidade',
    'pacote',
    'maço'
    );

create table if not exists products(
    id serial primary key,
    name text not null,
    unit_price numeric(6, 2) not null,
--     type product_type not null,
    quantity_per_unit real not null,
--     unity_measure measurement_unit not null,
    description text
);

insert into products(name, unit_price, quantity_per_unit, description)
values('Vodka', 25.00, 750, 'Vodka Slova'),
      ('Banana', 0.50, 1, 'banana maçã'),
      ('Coentro', 4.00,  1, 'coentro da roça'),
      ('Cebola', 7.00, 1, 'cebola branca'),
      ('Sabonete', 2.00, 1, 'sabonete even'),
      ('Limpador Multiuso', 3.55, 500,  'Veja X-R 29'),
      ('Pão Frances', 0.75, 1, 'pão francês integral'),
      ('Lamen', 1.55, 1, 'Nissin'),
      ('Whisky', 50.00, 1000, 'Natu Nobilis'),
      ('Tomate', 0.40, 1, 'Tomate cereja'),
      ('Salsa', 3.00, 1, 'Salsinha do quintal'),
      ('Mandioca', 5.00, 1, 'Mandioca da roça'),
      ('Shampoo', 3.00, 1, 'Palmolive'),
      ('Água Sanitária', 5.60, 500, 'Tubarão'),
      ('Biscoito', 1.50, 1, 'Melhor que bolacha'),
      ('Trigo', 6.00, 1, 'Trigo sem fermento');