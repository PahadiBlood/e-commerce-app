INSERT INTO category (id, name, description) VALUES
    (nextval('category_seq'), 'Medicines', 'Prescription and over-the-counter medicines'),
    (nextval('category_seq'), 'Medical Devices', 'Devices used for diagnosis or treatment'),
    (nextval('category_seq'), 'Personal Care', 'Daily health and hygiene products'),
    (nextval('category_seq'), 'Supplements', 'Vitamins and dietary supplements'),
    (nextval('category_seq'), 'First Aid', 'Emergency and first aid supplies');


INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (nextval('product_seq'), 'Paracetamol 500mg', 'Pain reliever and fever reducer', 200, 25.00, 1),
    (nextval('product_seq'), 'Ibuprofen 400mg', 'Anti-inflammatory painkiller', 150, 40.00, 1),
    (nextval('product_seq'), 'Amoxicillin 250mg', 'Antibiotic capsule', 100, 85.00, 1),
    (nextval('product_seq'), 'Cough Syrup', 'Relief from dry cough', 120, 60.00, 1),

    (nextval('product_seq'), 'Digital Thermometer', 'Accurate body temperature measurement', 80, 250.00, 51),
    (nextval('product_seq'), 'Blood Pressure Monitor', 'Automatic BP measuring device', 60, 2200.00, 51),
    (nextval('product_seq'), 'Glucometer', 'Blood sugar testing device', 70, 1800.00, 51),
    (nextval('product_seq'), 'Nebulizer', 'Respiratory treatment device', 40, 3500.00, 51),

    (nextval('product_seq'), 'Hand Sanitizer 500ml', 'Alcohol-based hand sanitizer', 300, 120.00, 101),
    (nextval('product_seq'), 'Face Mask Pack', 'Disposable surgical masks', 500, 200.00, 101),
    (nextval('product_seq'), 'Antibacterial Soap', 'Germ protection soap', 250, 45.00, 101),
    (nextval('product_seq'), 'Shampoo', 'Medicated anti-dandruff shampoo', 180, 180.00, 101),

    (nextval('product_seq'), 'Vitamin C Tablets', 'Immunity booster supplement', 220, 350.00, 151),
    (nextval('product_seq'), 'Calcium Tablets', 'Bone health supplement', 200, 300.00, 151),
    (nextval('product_seq'), 'Protein Powder', 'Dietary protein supplement', 90, 1200.00, 151),
    (nextval('product_seq'), 'Multivitamin Capsules', 'Daily nutrition supplement', 160, 450.00, 151),

    (nextval('product_seq'), 'First Aid Kit', 'Basic emergency medical kit', 75, 550.00, 201),
    (nextval('product_seq'), 'Bandage Roll', 'Elastic medical bandage', 400, 30.00, 201),
    (nextval('product_seq'), 'Antiseptic Liquid', 'Wound cleaning solution', 140, 95.00, 201),
    (nextval('product_seq'), 'Cotton Pads', 'Sterile cotton pads', 500, 70.00, 201);
