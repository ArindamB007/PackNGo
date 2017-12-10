/*User Data*/
INSERT INTO user (password,email,first_name,last_name,email_validated,created_timestamp,updated_timestamp,deleted_flag)
VALUES ('password','arindam.bandyopadhyay@gmail.com', 'Arindam','Bandyopadhyay',FALSE , now(),now(),FALSE );

/*Role Data*/
INSERT INTO role(name,description) VALUES ("ROLE_USER","An applicaiton user");

/*Property Data*/
INSERT INTO property (name,tagline,short_desc,description,created_timestamp,
                      updated_timestamp,enabled_flag)
VALUES ('Property XYZ','Heavenly Comfort',
        'Cozy Comfortable Stay',
        'This is a sprawling property in the heart of the city. Amazing location of the property and connections from this city makes this property and excellent option to spend your stay.',
        now(),now(),1);

INSERT INTO property (name,tagline,short_desc,description,created_timestamp,
                      updated_timestamp,enabled_flag)
VALUES ('Property EFGH','Earthly Comfort',
        'Warm Enjoyable Stay',
        'Amazing location of the property and connections from this city makes this property and excellent option to spend your stay. This is a sprawling property in the heart of the city.',
        now(),now(),1);

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
INSERT INTO image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,id_entity)
VALUES ('COVER','Cover Photo','../img/prop1_img/prop1_cover.jpg',now(),now(),1,
        (select id_property from property where name='Property XYZ'));
/*--------- Inserting Test Data---------*/
INSERT INTO image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,id_entity)
VALUES ('Test','Test Cover Photo','../img/prop1_img/test.jpg',now(),now(),1,
        (select id_property from property where name='Property XYZ'));
INSERT INTO image (name,short_desc,img_path,created_timestamp,updated_timestamp,enabled_flag,id_entity)
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

/*Room Type Data*/
INSERT INTO room_type (type_name,base_price, discount, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Deluxe',2500,0,'Our Deluxe rooms offer s unique combination of value and comfort. It comes at great pricing while offering all the basic amenities that will make you feel at home',
        now(),now(),1);
INSERT INTO room_type (type_name,base_price, discount, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Super Deluxe',2800,0,'Super Deluxe rooms take the comfort level much higher. It provided an excellent offering of super comfort and luxury at modest and affordable pricing',
        now(),now(),1);
INSERT INTO room_type (type_name,base_price, discount, description,created_timestamp,updated_timestamp,enabled_flag)
VALUES ('Suite',2800,0,'Our Suite rooms are built to spoil you. A cozy pampered stay for you away from how that is sure to make a long lasting mark whn you look back at this trip',
        now(),now(),1);

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

/*--------- Inserting Test Data---------*/
INSERT INTO booking (check_in_timestamp,check_out_timestamp,created_timestamp,updated_timestamp,enabled_flag)
    VALUES (CURDATE() + INTERVAL 2 DAY ,now(),now(),now(),1);

INSERT INTO bookings_rooms(id_booking,id_room)
    VALUES (1,10);