/*Role Data*/
INSERT INTO role(name,description) VALUES ("ROLE_USER","An applicaiton user");

/*User Data*/
INSERT INTO user (password,email,first_name,last_name,email_validated,created_timestamp,updated_timestamp,deleted_flag)
VALUES ('$2a$10$OoCsci6KuXyZoADVKs4Jw.NsgqTFUGYWWxSXNTE85F3EybjoDbu..','arindam.bandyopadhyay@gmail.com', 'Arindam','Bandyopadhyay',TRUE , now(),now(),FALSE );

INSERT INTO users_roles (id_user,id_role)
values((select id_user from user where email = 'arindam.bandyopadhyay@gmail.com'),
			 (select id_role from role where name = "ROLE_USER"));

INSERT INTO user (password,email,first_name,last_name,email_validated,created_timestamp,updated_timestamp,deleted_flag)
VALUES ('$2a$10$q2y/OlNVXSobdKN2zOLDUOlO49zVUGSQ4ZBTpuaNuEe6ZSc9b0Gbu','patraanup123@gmail.com', 'Anup','Patra',TRUE , now(),now(),FALSE );

INSERT INTO users_roles (id_user,id_role)
values((select id_user from user where email = 'patraanup123@gmail.com'),
			 (select id_role from role where name = "ROLE_USER"));

/*Property Data*/
INSERT INTO property (name,tagline,short_desc,description,location,created_timestamp,
                      updated_timestamp,enabled_flag)
VALUES ('Property XYZ','Heavenly Comfort',
        'Cozy Comfortable Stay',
        'This is a sprawling property in the heart of the city. Amazing location of the property and connections from this city makes this property and excellent option to spend your stay.',
        "Gangtok",now(),now(),1);

INSERT INTO property (name,tagline,short_desc,description,location,created_timestamp,
                      updated_timestamp,enabled_flag)
VALUES ('Property EFGH','Earthly Comfort',
        'Warm Enjoyable Stay',
        'Amazing location of the property and connections from this city makes this property and excellent option to spend your stay. This is a sprawling property in the heart of the city.',
        "Location",now(),now(),1);

/*Facility Data*/
INSERT INTO facility (name,css_class_name,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('WIFI','WIFI',now(),now(),1);
INSERT INTO facility (name,css_class_name,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Parking','Parking',now(),now(),1);
INSERT INTO facility (name,css_class_name,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Breakfast','Breakfast',now(),now(),1);
INSERT INTO facility (name,css_class_name,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Restaurant','Restaurant',now(),now(),1);

/*Property Image*/
INSERT INTO property_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,property_id)
VALUES ('COVER','Cover Photo','../img/prop1_img/prop1_cover.jpg',now(),now(),1,
        (select id_property from property where name='Property XYZ'));
/*--------- Inserting Test Data---------*/
INSERT INTO property_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,property_id)
VALUES ('Test','Test Cover Photo','../img/prop1_img/test.jpg',now(),now(),1,
        (select id_property from property where name='Property XYZ'));
INSERT INTO property_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,property_id)
VALUES ('COVER','Cover Photo','../img/prop2_img/prop2_cover.jpg',now(),now(),1,
        (select id_property from property where name='Property EFGH'));

/*Property Facilities*/
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property XYZ'),
        (select id_facility from facility where name='WIFI'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property XYZ'),
        (select id_facility from facility where name='Parking'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property XYZ'),
        (select id_facility from facility where name='Breakfast'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property XYZ'),
        (select id_facility from facility where name='Restaurant'));

INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property EFGH'),
        (select id_facility from facility where name='WIFI'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property EFGH'),
        (select id_facility from facility where name='Parking'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property EFGH'),
        (select id_facility from facility where name='Breakfast'));
INSERT INTO properties_facilities (id_property, id_facility)
VALUES ((select id_property from property where name='Property EFGH'),
        (select id_facility from facility where name='Restaurant'));

/*Room Type Data with property*/
INSERT INTO room_type (property_id_property,type_name,base_price, discount, max_adult_occupancy, max_child_occupancy, max_extra_adult_occupancy, max_extra_child_occupancy, max_total_occupancy, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_property from property where name='Property XYZ'),
        'Deluxe',2500,0,2,1,2,2,5,'Our Deluxe rooms offers unique combination of value and comfort. It comes at great pricing while offering all the basic amenities that will make you feel at home',
        now(),now(),1);
INSERT INTO room_type (property_id_property,type_name,base_price, discount, max_adult_occupancy,max_child_occupancy, max_extra_adult_occupancy, max_extra_child_occupancy, max_total_occupancy, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_property from property where name='Property XYZ'),
        'Super Deluxe',2800,0,2,1,2,2,5,'Super Deluxe rooms take the comfort level much higher. It provided an excellent offering of super comfort and luxury at modest and affordable pricing',
        now(),now(),1);
INSERT INTO room_type (property_id_property,type_name,base_price, discount, max_adult_occupancy, max_child_occupancy, max_extra_adult_occupancy, max_extra_child_occupancy, max_total_occupancy, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_property from property where name='Property XYZ'),
        'Suite',3000,0,2,1,2,2,5,'Our Suite rooms are built to spoil you. A cozy pampered stay for you away from home, that is sure to make a long lasting mark when you look back at this trip years later',
        now(),now(),1);
INSERT INTO room_type (property_id_property,type_name,base_price, discount, max_adult_occupancy, max_child_occupancy, max_extra_adult_occupancy, max_extra_child_occupancy, max_total_occupancy, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_property from property where name='Property EFGH'),
        'Executive',3500,0,2,1,2,2,5,'A super cozy pampered stay for you away from home, that is sure to make a long lasting mark when you look back at this trip years later',
        now(),now(),1);

/*Item Tax Data*/
INSERT INTO item_tax (item_tax_code,item_tax_description,item_tax_percent,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("CGST", "CGST",0,now(),now(),1);
INSERT INTO item_tax (item_tax_code,item_tax_description,item_tax_percent,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("SGST", "SGST",0,now(),now(),1);


/*Item Type Data*/
/* denotes if item is of the following types 
 * - REGULARITEM
 * - MEALPLANITEM
 * - EXTRABEDADULT
 * - EXTRABEDCHILD 
 * - TRNSPORTITEM
 * - FOCITEM
 * - COUPONNITEM*/
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("MEALPLANITEM", "This is a meal plan type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("EXTRABEDADULT", "This is an adult extra bed type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("EXTRABEDCHILD", "This is a child extra bed type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("REGULARITEM", "This is a regular type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("TRNSPORTITEM", "This is a transport type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("FOCITEM", "This is a free of cost type item",now(),now(),1);
INSERT INTO item_type (item_type_code,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("COUPONNITEM", "This is a coupon type item",now(),now(),1);

/*Item Price, Item (Meal Plan & Xtra Bed) and Mealplan data - start*/

/*Deluxe EP*/

/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (2500,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Deluxe Room - EP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (700,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Deluxe Extra Bed Adult - EP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (500,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Deluxe Extra Bed CHILD - EP",now(),now(),1);


/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("EP",(select id_room_type from room_type where type_name='Deluxe'), 
"Accomodation only",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name= 'Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Room - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Adult - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed CHILD - EP'));
		
		
/*Deluxe CP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (2800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Deluxe Room - CP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Deluxe Extra Bed Adult - CP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (600,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Deluxe Extra Bed Child - CP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("CP",(select id_room_type from room_type where type_name='Deluxe'),
"Accomodation with complimentary breakfast",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Room - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Adult - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Child - CP'));



/*Deluxe MAP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (3500,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Deluxe Room - MAP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (900,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Deluxe Extra Bed Adult - MAP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (700,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Deluxe Extra Bed Child - MAP",now(),now(),1);


/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("MAP",(select id_room_type from room_type where type_name='Deluxe'),
"Accomodation with complimentary breakfast and one meal (Lunch or Dinner)",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Room - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Adult - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type 
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Child - MAP'));


/*Deluxe AP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (4400,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Deluxe Room - AP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1000,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Deluxe Extra Bed Adult - AP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Deluxe Extra Bed Child - AP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("AP",(select id_room_type from room_type where type_name='Deluxe'),
"Accomodation with complimentary breakfast and all meals",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Room - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Adult - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Deluxe'),
		(SELECT id_item from item where description = 'Deluxe Extra Bed Child - AP'));


/*Super Deluxe EP*/

/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (2700,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Super Deluxe Room - EP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Super Deluxe Extra Bed Adult - EP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (600,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Super Deluxe Extra Bed Child - EP",now(),now(),1);


/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("EP",(select id_room_type from room_type where type_name='Super Deluxe'), 
"Accomodation only",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Room - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Adult - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Child - EP'));

/*Super Deluxe CP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (3000,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Super Deluxe Room - CP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (900,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Super Deluxe Extra Bed Adult - CP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (700,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Super Deluxe Extra Bed Child - CP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("CP",(select id_room_type from room_type where type_name='Super Deluxe'),
"Accomodation with complimentary breakfast",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Room - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Adult - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Child - CP'));


/*Super Deluxe MAP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (3700,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Super Deluxe Room - MAP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1000,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Super Deluxe Extra Bed Adult - MAP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Super Deluxe Extra Bed Child - MAP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("MAP",(select id_room_type from room_type where type_name='Super Deluxe'),
"Accomodation with complimentary breakfast and one meal (Lunch or Dinner)",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Room - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Adult - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Child - MAP'));


/*Super Deluxe AP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (4600,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Super Deluxe Room - AP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1100,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Super Deluxe Extra Bed Adult - AP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (900,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Super Deluxe Extra Bed Child - AP",now(),now(),1);


/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("AP",(select id_room_type from room_type where type_name='Super Deluxe'),
"Accomodation with complimentary breakfast and all meals",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Room - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Adult - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Super Deluxe'),
		(SELECT id_item from item where description = 'Super Deluxe Extra Bed Child - AP'));


/*Suite EP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (2900,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Suite Room - EP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1200,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Suite Extra Bed Adult - EP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1000,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Suite Extra Bed Child - EP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("EP",(select id_room_type from room_type where type_name='Suite'),
"Accomodation only",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Room - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Adult - EP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "EP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Child - EP'));

/*Suite CP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (3200,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Suite Room - CP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1300,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Suite Extra Bed Adult - CP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1100,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Suite Extra Bed Child - CP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("CP",(select id_room_type from room_type where type_name='Suite'),
"Accomodation with complimentary breakfast",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Room - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Adult - CP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "CP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Child - CP'));

/*Suite MAP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (3900,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Suite Room - MAP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1400,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Suite Extra Bed Adult - MAP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1200,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Suite Extra Bed Child - MAP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("MAP",(select id_room_type from room_type where type_name='Suite'),
"Accomodation with complimentary breakfast and one meal (Lunch or Dinner)",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Room - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Adult - MAP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "MAP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Child - MAP'));

/*Suite AP*/
/*Meal Plan Item Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (4800,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='MEALPLANITEM'), 
last_insert_id(), "Suite Room - AP",now(),now(),1);

/*Adult Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1500,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDADULT'), 
last_insert_id(), "Suite Extra Bed Adult - AP",now(),now(),1);

/*Child Extra Bed Price and Item*/
INSERT INTO item_price (base_price,created_timestamp,updated_timestamp,enabled_flag)
VALUES (1300,now(),now(),1);
INSERT INTO item (item_type_id_item_type, item_price_id ,description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ((select id_item_type from item_type where item_type_code='EXTRABEDCHILD'), 
last_insert_id(), "Suite Extra Bed Child - AP",now(),now(),1);

/*Insert meal plan and link items*/
INSERT INTO meal_plan (meal_plan_code, room_type_id, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ("AP",(select id_room_type from room_type where type_name='Suite'),
"Accomodation with complimentary breakfast and all meals",now(),now(),1);

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Room - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Adult - AP'));

INSERT INTO meal_plan_items (id_meal_plan,id_item)
VALUES ((SELECT id_meal_plan from meal_plan 
			INNER JOIN room_type
				ON meal_plan.room_type_id = room_type.id_room_type
				AND meal_plan.meal_plan_code = "AP"
				AND room_type.type_name='Suite'),
		(SELECT id_item from item where description = 'Suite Extra Bed Child - AP'));

/*Item Price, Item and Mealplan data - end*/


/*Room Type Facility data*/
/*Deluxe*/
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Deluxe'),
  (Select id_facility from facility where name ='WIFI'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Deluxe'),
  (select id_facility from facility where name='Parking'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Deluxe'),
  (select id_facility from facility where name='Breakfast'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Deluxe'),
  (select id_facility from facility where name='Restaurant'));

/*Super Deluxe*/
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Super Deluxe'),
  (Select id_facility from facility where name ='WIFI'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Super Deluxe'),
  (select id_facility from facility where name='Parking'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Super Deluxe'),
  (select id_facility from facility where name='Breakfast'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Super Deluxe'),
  (select id_facility from facility where name='Restaurant'));

/*Suite*/
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Suite'),
  (Select id_facility from facility where name ='WIFI'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Suite'),
  (select id_facility from facility where name='Parking'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Suite'),
  (select id_facility from facility where name='Breakfast'));
INSERT INTO room_types_facilities (id_room_type, id_facility)
VALUES (
  (select id_room_type from room_type where type_name='Suite'),
  (select id_facility from facility where name='Restaurant'));
  
/*Room Type Image*/
INSERT INTO room_type_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,room_type_id)
VALUES ('COVER','Deluxe Cover','../img/prop1_img/rooms/delux_cover.jpg',now(),now(),1,
        (select id_room_type from room_type where type_name='Deluxe'));
INSERT INTO room_type_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,room_type_id)
VALUES ('COVER','Super Deluxe Cover','../img/prop1_img/rooms/super_delux_cover.jpg',now(),now(),1,
        (select id_room_type from room_type where type_name='Super Deluxe'));
INSERT INTO room_type_image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,room_type_id)
VALUES ('COVER','Suite','../img/prop1_img/rooms/suite_cover.jpg',now(),now(),1,
        (select id_room_type from room_type where type_name='Suite'));

/*Room data with types*/
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('1',now(),now(),1, (select id_room_type from room_type where type_name='Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('2',now(),now(),1, (select id_room_type from room_type where type_name='Super Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('3',now(),now(),1, (select id_room_type from room_type where type_name='Super Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('4',now(),now(),1, (select id_room_type from room_type where type_name='Suite'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('5',now(),now(),1, (select id_room_type from room_type where type_name='Suite'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('6',now(),now(),1, (select id_room_type from room_type where type_name='Super Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('7',now(),now(),1, (select id_room_type from room_type where type_name='Super Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('8',now(),now(),1, (select id_room_type from room_type where type_name='Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('9',now(),now(),1, (select id_room_type from room_type where type_name='Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('10',now(),now(),1, (select id_room_type from room_type where type_name='Deluxe'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('11',now(),now(),1, (select id_room_type from room_type where type_name='Executive'));
INSERT INTO room (room_no,created_timestamp,updated_timestamp,enabled_flag,room_type_id_room_type)
VALUES ('12',now(),now(),1, (select id_room_type from room_type where type_name='Executive'));


/*--------- Inserting Test Data---------*/
INSERT INTO booking (check_in_timestamp,check_out_timestamp,created_timestamp,updated_timestamp,enabled_flag)
    VALUES ('2018-09-16 11:00:00.0','2018-09-18 11:00:00.0',now(),now(),1);

INSERT INTO bookings_rooms(id_booking,id_room)
    VALUES (1,10);
    
INSERT INTO booking (check_in_timestamp,check_out_timestamp,created_timestamp,updated_timestamp,enabled_flag)
    VALUES ('2018-09-12 11:00:00.0','2018-09-15 11:00:00.0',now(),now(),1);

INSERT INTO bookings_rooms(id_booking,id_room)
    VALUES (2,5);

INSERT INTO booking (check_in_timestamp,check_out_timestamp,created_timestamp,updated_timestamp,enabled_flag)
    VALUES ('2018-09-12 11:00:00.0','2018-09-15 10:00:00.0',now(),now(),1);

INSERT INTO bookings_rooms(id_booking,id_room)
    VALUES (3,9);

INSERT INTO booking (check_in_timestamp,check_out_timestamp,created_timestamp,updated_timestamp,enabled_flag)
    VALUES ('2018-09-16 11:00:00.0','2018-09-20 11:00:00.0',now(),now(),1);

INSERT INTO bookings_rooms(id_booking,id_room)
    VALUES (4,4);
    
/*
 select room_type.id_room_type,room_type.type_name,room_type.base_price,count(*) as count_available,
    room_type.discount,room_type.description
    from room 
		LEFT JOIN room_type
			on room.room_type_id_room_type = room_type.id_room_type
		LEFT JOIN bookings_rooms
			on room.id_room = bookings_rooms.id_room
		LEFT JOIN booking
			on booking.id_booking = bookings_rooms.id_booking
		INNER JOIN room_type as rt
			on rt.id_room_type = room_type.id_room_type AND
			((booking.check_in_timestamp IS NULL AND  booking.check_out_timestamp IS NULL) OR
            ('2017-12-17 00:00:01' < booking.check_in_timestamp and '2017-12-18 00:00:00' < booking.check_in_timestamp) OR
			('2017-12-17 00:00:01' > booking.check_out_timestamp and '2017-12-18 00:00:00' > booking.check_out_timestamp))
		group by type_name order by room_type.id_room_type;
 */    
    
    /*
     TypedQuery<Object[]> q = getEntityManager().createQuery(
    "SELECT c.id, count(p.id) " +
    "FROM Product p LEFT JOIN p.category c " +
    "WHERE p.seller.id = :id " +
    "GROUP BY c.id", Object[].class).setParameter("id", id);

List<Object[]> resultList = q.getResultList();
Map<String, Long> resultMap = new HashMap<String, Long>(resultList.size());
for (Object[] result : resultList)
  resultMap.put((String)result[0], (Long)result[1]);
     */
    
    