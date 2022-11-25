--TODOS LOS SITE QUE TENGAN CARGADO LAT Y LONG GEODÉSICA
-- es importante el orden site_no,latitude,longitude !NO ALTERARLO!
select site_no,latitude,longitude from site
where (latitude is not null and longitude is not null) --37987
--PERO QUE NO SEAN LOS QUE YA ESTÁN EN SITE_OPTION CON ALGUN VALOR
AND SITE_NO NOT IN
(
select site_no from site_option where option_id='LAT' and option_value is NOT null
UNION
select site_no from site_option where option_id='LON' and option_value is NOT null
)