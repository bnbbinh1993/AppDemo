package vn.bn.teams.appdemo.data.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import vn.bn.teams.appdemo.data.models.DataAlphabetFollow
import vn.bn.teams.appdemo.data.models.DataFollow

import java.lang.Exception

class MyDatabase(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "LanguageForKid"
        private val DB_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE BIGLIST(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Image TEXT, Sound TEXT)")
        db?.execSQL("CREATE TABLE DETAIL(id INTEGER PRIMARY KEY AUTOINCREMENT, id_big INTEGER, dt_image TEXT, dt_sound TEXT)")
        insertToDB(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS BIGLIST")
        db?.execSQL("DROP TABLE IF EXISTS DETAIL")
        onCreate(db)
    }
    fun insertToDB(db: SQLiteDatabase?) {
        // Insert to BIGLIST
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('BẢNG CHỮ CÁI', 'img_alphabet_a', 'sounds_start_bangchucai')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('CHỮ SỐ', 'img_number_0', 'sounds_start_chuso')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('ĐỘNG VẬT', 'img_animal_ant', 'sounds_start_cacloaidongvat')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('CÁC LOẠI HOA QUẢ', 'img_fruit_apple', 'sounds_start_cacloaihoaqua')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('HÌNH HỌC', 'img_geometry_square', 'sounds_start_cacdanghinhhoc')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('ĐỒ DÙNG HỌC TẬP', 'img_school_bag', 'sounds_start_dodunghoctap')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('MÀU SẮC', 'img_color_blue', 'sounds_start_cacloaimausac')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('CÁC LOẠI HOA', 'img_flower_apricotblossom', 'sounds_start_cacloaihoa')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('GIAO THÔNG', 'img_vehicle_ambulance', 'sounds_start_phuongtiengiaothong')")
        db?.execSQL("INSERT INTO BIGLIST(Name, Image, Sound)VALUES('QUỐC GIA', 'img_country_vietnam', 'sounds_start_quocgia')")
        // Insert DETAIL Alphabet
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_a', 'sounds_alphabet_a')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_b', 'sounds_alphabet_b')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_c', 'sounds_alphabet_c')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_d', 'sounds_alphabet_d')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_e', 'sounds_alphabet_e')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_g', 'sounds_alphabet_g')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_h', 'sounds_alphabet_h')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_i', 'sounds_alphabet_i')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_k', 'sounds_alphabet_k')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_l', 'sounds_alphabet_l')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_m', 'sounds_alphabet_m')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_n', 'sounds_alphabet_n')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_o', 'sounds_alphabet_o')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_p', 'sounds_alphabet_p')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_q', 'sounds_alphabet_q')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_r', 'sounds_alphabet_r')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_s', 'sounds_alphabet_s')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_t', 'sounds_alphabet_t')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_u', 'sounds_alphabet_u')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_v', 'sounds_alphabet_v')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_x', 'sounds_alphabet_x')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(1, 'img_alphabet_y', 'sounds_alphabet_y')")
        //Insert DETAIL number
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_0', 'sounds_number_0')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_1', 'sounds_number_1')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_2', 'sounds_number_2')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_3', 'sounds_number_3')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_4', 'sounds_number_4')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_5', 'sounds_number_5')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_6', 'sounds_number_6')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_7', 'sounds_number_7')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_8', 'sounds_number_8')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_9', 'sounds_number_9')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_10', 'sounds_number_10')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_11', 'sounds_number_11')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_12', 'sounds_number_12')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_13', 'sounds_number_13')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_14', 'sounds_number_14')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_15', 'sounds_number_15')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_16', 'sounds_number_16')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_17', 'sounds_number_17')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_18', 'sounds_number_18')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_19', 'sounds_number_19')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(2, 'img_number_20', 'sounds_number_20')")
        //Insert DETAIL animals
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_ant', 'sounds_animal_ant')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_bear', 'sounds_animal_bear')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_bee', 'sounds_animal_bee')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_bird', 'sounds_animal_bird')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_buffalo', 'sounds_animal_buffalo')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_butterfly', 'sounds_animal_butterfly')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_cat', 'sounds_animal_cat')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_cow', 'sounds_animal_cow')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_deer', 'sounds_animal_deer')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_dog', 'sounds_animal_dog')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_dolphin', 'sounds_animal_dolphin')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_duck', 'sounds_animal_duck')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_elephant', 'sounds_animal_elephant')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_fox', 'sounds_animal_fox')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_frog', 'sounds_animal_frog')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_giraffe', 'sounds_animal_giraffe')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_goat', 'sounds_animal_goat')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_hen', 'sounds_animal_hen')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_hippo', 'sounds_animal_hippo')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_hippocampus', 'sounds_animal_hippocampus')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_horse', 'sounds_animal_horse')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_lion', 'sounds_animal_lion')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_monkey', 'sounds_animal_monkey')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_mouse', 'sounds_animal_mouse')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_ostrich', 'sounds_animal_ostrich')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_panda', 'sounds_animal_panda')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_panther', 'sounds_animal_panther')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_penguin', 'sounds_animal_penguin')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_pig', 'sounds_animal_pig')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_rabbit', 'sounds_animal_rabbit')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_rhino', 'sounds_animal_rhino')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_rooster', 'sounds_animal_rooster')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_shark', 'sounds_animal_shark')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_sheep', 'sounds_animal_sheep')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_snail', 'sounds_animal_snail')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_snake', 'sounds_animal_snake')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_squirrel', 'sounds_animal_squirrel')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_tiger', 'sounds_animal_tiger')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_turtle', 'sounds_animal_turtle')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(3, 'img_animal_zebra', 'sounds_animal_zebra')")
        // Insert DETAIL fruit
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_apple', 'sounds_fruit_apple')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_apricot', 'sounds_fruit_apricot')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_avocado', 'sounds_fruit_avocado')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_banana', 'sounds_fruit_banana')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_blueberry', 'sounds_fruit_blueberry')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_cantaloupe', 'sounds_fruit_cantaloupe')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_carrot', 'sounds_fruit_carrot')")
      //  db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_cherry', 'sounds_fruit_cherry')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_corncob', 'sounds_fruit_corncob')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_cucumber', 'sounds_fruit_cucumber')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_dragonfruit', 'sounds_fruit_dragonfruit')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_durian', 'sounds_fruit_durian')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_eggplant', 'sounds_fruit_eggplant')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_grape', 'sounds_fruit_grape')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_guava', 'sounds_fruit_guava')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_jackfruit', 'sounds_fruit_jackfruit')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_kiwi', 'sounds_fruit_kiwi')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_lemon', 'sounds_fruit_lemon')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_longan', 'sounds_fruit_longan')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_lychee', 'sounds_fruit_lychee')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_mango', 'sounds_fruit_mango')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_mangosteen', 'sounds_fruit_mangosteen')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_mulberry', 'sounds_fruit_mulberry')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_orange', 'sounds_fruit_orange')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_papaya', 'sounds_fruit_papaya')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_peaches', 'sounds_fruit_peaches')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_pear', 'sounds_fruit_pear')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_persimmon', 'sounds_fruit_persimmon')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_pineapple', 'sounds_fruit_pineapple')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_pomegranate', 'sounds_fruit_pomegranate')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_pomelo', 'sounds_fruit_pomelo')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_potato', 'sounds_fruit_potato')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_pumpkin', 'sounds_fruit_pumpkin')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_rambutan', 'sounds_fruit_rambutan')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_sapodilla', 'sounds_fruit_sapodilla')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_soursoup', 'sounds_fruit_soursoup')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_starapple', 'sounds_fruit_starapple')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_starfruit', 'sounds_fruit_starfruit')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_strawberry', 'sounds_fruit_strawberry')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_sweetpotato', 'sounds_fruit_sweetpotato')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_tamarind', 'sounds_fruit_tamarind')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_tomato', 'sounds_fruit_tomato')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(4, 'img_fruit_watermelon', 'sounds_fruit_watermelon')")
        //Insert DETAIL geometry
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_square', 'sounds_geometry_square')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_circle', 'sounds_geometry_circle')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_heart', 'sounds_geometry_heart')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_hexagon', 'sounds_geometry_hexagon')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_oval', 'sounds_geometry_oval')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_parallelogram', 'sounds_geometry_parallelogram')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_pentagon', 'sounds_geometry_pentagon')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_rectangle', 'sounds_geometry_rectangle')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_star', 'sounds_geometry_star')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_trapezoid', 'sounds_geometry_trapezoid')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(5, 'img_geometry_triangular', 'sounds_geometry_triangular')")
        //Insert DETAIL school
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_bag', 'sounds_school_bag')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_book', 'sounds_school_book')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_colorbox', 'sounds_school_colorbox')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_compass', 'sounds_school_compass')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_eraser', 'sounds_school_eraser')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_globe', 'sounds_school_globe')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_glue', 'sounds_school_glue')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_highlighter', 'sounds_school_highlighter')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_magnifyingglass', 'sounds_school_magnifyingglass')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_marker', 'sounds_school_marker')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_notebook', 'sounds_school_notebook')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_pen', 'sounds_school_pen')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_penbox', 'sounds_school_penbox')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_pencil', 'sounds_school_pencil')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_postitnotes', 'sounds_school_postitnotes')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_protractor', 'sounds_school_protractor')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_scissors', 'sounds_school_scissors')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_scotchtape', 'sounds_school_scotchtape')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_setsquare', 'sounds_school_setsquare')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_sharpener', 'sounds_school_sharpener')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_stapler', 'sounds_school_stapler')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_tapemeasure', 'sounds_school_tapemeasure')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_testtube', 'sounds_school_testtube')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(6, 'img_school_watercolour', 'sounds_school_watercolour')")
        //Insert DETAIL color
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_black', 'sounds_color_black')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_blue', 'sounds_color_blue')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_brown', 'sounds_color_brown')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_green', 'sounds_color_green')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_orange', 'sounds_color_orange')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_pink', 'sounds_color_pink')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_purple', 'sounds_color_purple')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_red', 'sounds_color_red')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_white', 'sounds_color_white')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(7, 'img_color_yellow', 'sounds_color_yellow')")
        //Insert DETAIL flowers
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_apricotblossom', 'sounds_flower_apricotblossom')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_azaleas', 'sounds_flower_azaleas')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_bellflower', 'sounds_flower_bellflower')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_carnation', 'sounds_flower_carnation')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_chrysanthemum', 'sounds_flower_chrysanthemum')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_confetti', 'sounds_flower_confetti')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_dahlia', 'sounds_flower_dahlia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_dandelion', 'sounds_flower_dandelion')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_flamingoflower', 'sounds_flower_flamingoflower')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_gladiolus', 'sounds_flower_gladiolus')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_hyacinth', 'sounds_flower_hyacinth')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_hydrangea', 'sounds_flower_hydrangea')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_jasmine', 'sounds_flower_jasmine')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_lagerstroemia', 'sounds_flower_lagerstroemia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_lily', 'sounds_flower_lily')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_lotus', 'sounds_flower_lotus')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_narcissus', 'sounds_flower_narcissus')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_orchid', 'sounds_flower_orchid')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_peachblossom', 'sounds_flower_peachblossom')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_peony', 'sounds_flower_peony')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_rose', 'sounds_flower_rose')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_sunflower', 'sounds_flower_sunflower')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_tuberose', 'sounds_flower_tuberose')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_tulipa', 'sounds_flower_tulipa')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_violet', 'sounds_flower_violet')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(8, 'img_flower_waterlily', 'sounds_flower_waterlily')")
        //Insert DETAIL vehicle
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_ambulance', 'sounds_vehicle_ambulance')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_babycarriage', 'sounds_vehicle_babycarriage')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_bike', 'sounds_vehicle_bike')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_bulldozer', 'sounds_vehicle_bulldozer')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_bus', 'sounds_vehicle_bus')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_canoe', 'sounds_vehicle_canoe')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_car', 'sounds_vehicle_car')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_cargoship', 'sounds_vehicle_cargoship')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_concretemixertruck', 'sounds_vehicle_concretemixertruck')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_cranetruck', 'sounds_vehicle_cranetruck')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_cyclo', 'sounds_vehicle_cyclo')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_ferry', 'sounds_vehicle_ferry')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_firetruck', 'sounds_vehicle_firetruck')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_forklift', 'sounds_vehicle_forklift')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_glider', 'sounds_vehicle_glider')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_helicopter', 'sounds_vehicle_helicopter')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_hotairballoon', 'sounds_vehicle_hotairballoon')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_motorbike', 'sounds_vehicle_motorbike')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_policecar', 'sounds_vehicle_policecar')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_racecar', 'sounds_vehicle_racecar')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_roller', 'sounds_vehicle_roller')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_sailboat', 'sounds_vehicle_sailboat')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_ship', 'sounds_vehicle_ship')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_skateboard', 'sounds_vehicle_skateboard')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_submarine', 'sounds_vehicle_submarine')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_tank', 'sounds_vehicle_tank')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_train', 'sounds_vehicle_train')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(9, 'img_vehicle_truck', 'sounds_vehicle_truck')")
        //Insert DETAIL country
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_vietnam', 'sounds_country_vietnam')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_algeria', 'sounds_country_algeria')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_america', 'sounds_country_america')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_argentina', 'sounds_country_argentina')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_australia', 'sounds_country_australia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_austria', 'sounds_country_austria')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_belgium', 'sounds_country_belgium')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_brazil', 'sounds_country_brazil')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_brunei', 'sounds_country_brunei')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_canada', 'sounds_country_canada')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_chile', 'sounds_country_chile')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_china', 'sounds_country_china')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_combodia', 'sounds_country_combodia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_denmark', 'sounds_country_denmark')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_egypt', 'sounds_country_egypt')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_england', 'sounds_country_england')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_france', 'sounds_country_france')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_germany', 'sounds_country_germany')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_hungary', 'sounds_country_hungary')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_india', 'sounds_country_india')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_indonesia', 'sounds_country_indonesia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_iran', 'sounds_country_iran')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_iraq', 'sounds_country_iraq')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_israel', 'sounds_country_israel')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_italy', 'sounds_country_italy')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_japan', 'sounds_country_japan')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_korea', 'sounds_country_korea')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_laos', 'sounds_country_laos')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_libya', 'sounds_country_libya')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_malaysia', 'sounds_country_malaysia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_mexico', 'sounds_country_mexico')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_mongolia', 'sounds_country_mongolia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_myanmar', 'sounds_country_myanmar')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_netherlands', 'sounds_country_netherlands')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_norway', 'sounds_country_norway')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_paraguay', 'sounds_country_paraguay')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_philippine', 'sounds_country_philippine')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_poland', 'sounds_country_poland')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_portugal', 'sounds_country_portugal')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_qatar', 'sounds_country_qatar')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_russia', 'sounds_country_russia')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_singapore', 'sounds_country_singapore')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_southafrica', 'sounds_country_southafrica')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_spain', 'sounds_country_spain')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_sweden', 'sounds_country_sweden')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_switzerland', 'sounds_country_switzerland')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_syria', 'sounds_country_syria')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_thailand', 'sounds_country_thailand')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_timorleste', 'sounds_country_timorleste')")
        db?.execSQL("INSERT INTO DETAIL(id_big, dt_image, dt_sound)VALUES(10, 'img_country_uruguay', 'sounds_country_uruguay')")
    }
    @SuppressLint("Range")
    fun getAllItemsBigList(): ArrayList<DataFollow> {
        val list: ArrayList<DataFollow> = ArrayList()
        val selectQuery = "SELECT * FROM BIGLIST"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var name: String
        var image: String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex("Name"))
                image = cursor.getString(cursor.getColumnIndex("Image"))
                sound = cursor.getString(cursor.getColumnIndex("Sound"))
                val data = DataFollow(title = name, img_content = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsAlphabet():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 1"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image: String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsAnimal():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 3"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsNumber():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 2"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsFruit():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 4"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsGeometry():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 5"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsSchool():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 6"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsColor():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 7"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsFlower():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 8"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsVehicle():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 9"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getAllItemsCountry():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 10"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image:String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }
    private fun getSoundsId(name: String?, context: Context): Int? {
        return context?.resources?.getIdentifier(name, "raw", context.packageName)
    }
    @SuppressLint("Range")
    fun getSoundtemsNumber(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 2"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsAlphabet(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 1"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsAnimal(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 3"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsFruit(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 4"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsGeometry(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 5"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemSchool(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 6"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsColor(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 7"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsFlower(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 8"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsVehicle(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 9"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getSoundtemsCountry(context: Context):ArrayList<Int> {
        val list:ArrayList<Int> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL WHERE id_big = 10"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                getSoundsId(sound, context )?.let { list.add(it) }
            } while (cursor.moveToNext())
        }
        return list
    }
    @SuppressLint("Range")
    fun getTest():ArrayList<DataAlphabetFollow> {
        val list:ArrayList<DataAlphabetFollow> = ArrayList()
        val selectQuery = "SELECT * FROM DETAIL ORDER BY random() LIMIT 40"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var image: String
        var sound: String
        if (cursor.moveToFirst()) {
            do {
                image = cursor.getString(cursor.getColumnIndex("dt_image"))
                sound = cursor.getString(cursor.getColumnIndex("dt_sound"))
                val data = DataAlphabetFollow(image = image, sound = sound)
                list.add(data)
            } while (cursor.moveToNext())
        }
        return list
    }


}