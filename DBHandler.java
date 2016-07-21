package com.mobilesiri.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "publicspacestinder";
    // User table name
    private static final String TABLE_USER = "User";
    // User Table Columns names
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "password";

    // Place table name
    private static final String TABLE_PLACE = "Place";
    // Place Table Columns names
    private static final String KEY_PNAME = "PName";
    private static final String KEY_ADDRESS = "Address";
    private static final String KEY_LATITUDE = "Latitude";
    private static final String KEY_LONGITUDE = "Longitude";
    private static final String KEY_CITY = "City";
    private static final String KEY_FILTER = "Filter";

    // Filter table name
    private static final String TABLE_FILTERS = "Filters";
    
    // PlacePics table name
    private static final String TABLE_PLACEPICS = "PlacePics";
    // PlacePics Table Columns names
    private static final String KEY_IMG = "ImageFile";

    // PlaceList table name
    private static final String TABLE_PLACELIST = "PlaceList";
    

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
        + KEY_USERNAME + " VARCHAR(25) PRIMARY KEY," + KEY_EMAIL + " VARCHAR(25),"
        + KEY_ADDRESS + " VARCHAR(25)" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_FILTER_TABLE = "CREATE TABLE " + TABLE_FILTERS + "("
        + KEY_FILTER + " VARCHAR(25)" + ")";
        
        db.execSQL(CREATE_FILTER_TABLE);

        String CREATE_PLACE_TABLE = "CREATE TABLE " + TABLE_PLACE + "("
        + KEY_PNAME + " VARCHAR(25)," + KEY_ADDRESS + " VARCHAR(40),"
        + KEY_LATITUDE + "DOUBLE" + KEY_LONGITUDE + " DOUBLE,"
        + KEY_CITY + " VARCHAR(25)" + KEY_FILTER + " VARCHAR(25),"
        + "FOREIGN KEY (" + KEY_FILTER + " REFERENCES Filters(" + KEY_FILTER + "),"
        + "PRIMARY KEY (" + KEY_LATITUDE + ", " + KEY_LONGITUDE + ")" + ")";
        db.execSQL(CREATE_PLACE_TABLE);

        String CREATE_PLACEPICS_TABLE = "CREATE TABLE " + TABLE_PLACEPICS + "("
            + KEY_LATITUDE + " DOUBLE," + KEY_LONGITUDE + " DOUBLE,"
            + KEY_IMG + " VARCHAR(100),"
            + "FOREIGN KEY (" + KEY_LATITUDE + " REFERENCES Place(" + KEY_LATITUDE + "),"
            + "FOREIGN KEY (" + KEY_LONGITUDE + " REFERENCES Place(" + KEY_LONGITUDE + "),"
            + "PRIMARY KEY (" + KEY_LATITUDE + ", " + KEY_LONGITUDE ", " + KEY_IMG + ")" + ")";
        db.execSQL(CREATE_PLACEPICS_TABLE);

        String CREATE_PLACELIST_TABLE = "CREATE TABLE " + TABLE_PLACELIST + "("
            + KEY_LATITUDE + " DOUBLE," + KEY_LONGITUDE + " DOUBLE,"
            + KEY_USERNAME + " VARCHAR(25),"
            + "FOREIGN KEY (" + KEY_LATITUDE + " REFERENCES Place(" + KEY_LATITUDE + "),"
            + "FOREIGN KEY (" + KEY_LONGITUDE + " REFERENCES Place(" + KEY_LONGITUDE + "),"
            + "FOREIGN KEY (" + KEY_USERNAME + " REFERENCES User(" + KEY_USERNAME + "),"
            + "PRIMARY KEY (" + KEY_LATITUDE + ", " + KEY_LONGITUDE ", " + KEY_USERNAME + ")" + ")";
        db.execSQL(CREATE_PLACELIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Creating tables again
        onCreate(db);
    }

    // Adding new user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername()); // User Username
        values.put(KEY_EMAIL, user.getEmail()); // User Email
        values.put(KEY_PASSWORD, user.getPassword()); // User Password
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    // Adding new filter
    public void addUser(Filter filter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FILTER, filter.getFilter()); // Filter Filter
        // Inserting Row
        db.insert(TABLE_FILTERS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new place
    public void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PNAME, place.getPname()); // Place PName
        values.put(KEY_ADDRESS, place.getAddress()); // Place Address
        values.put(KEY_LATITUDE, place.getLatitude()); // Place Latitude
        values.put(KEY_LONGITUDE, place.getLongitude()); // Place Longitude
        values.put(KEY_CITY, place.getCity()); // Place City
        values.put(KEY_FILTER, place.getFilter()); // Place Filter
        // Inserting Row
        db.insert(TABLE_PLACE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new place pic
    public void addPlacePic(PlacePic placepic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, placepic.getLatitude()); // Placepic Latitude
        values.put(KEY_LONGITUDE, placepic.getLongitude()); // Placepic Longitude
        values.put(KEY_IMG, placepic.getImagefile()); // Placepic ImageFile
        // Inserting Row
        db.insert(TABLE_PLACEPICS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new place list
    public void addPlaceList(PlaceList placelist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LATITUDE, placelist.getLatitude()); // Placelist Latitude
        values.put(KEY_LONGITUDE, placelist.getLongitude()); // Placelist Longitude
        values.put(KEY_USERNAME, placelist.getUsername()); // Placelist Username
        // Inserting Row
        db.insert(TABLE_PLACELIST, null, values);
        db.close(); // Closing database connection
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USERNAME,
        KEY_EMAIL, KEY_PASSWORD }, KEY_USERNAME + " =?",
        new String[] { username }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        User user = new User(cursor.getString(0), cursor.getString(1), 
            cursor.getString(2));
        // return user
        return user;
    }

    public Filter getFilter(String filtername) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FILTERS, new String[] { KEY_FILTER },
        KEY_FILTER + " =?",
        new String[] { filtername }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        Filter filter = new Filter(cursor.getString(0));
        // return filter
        return filter;
    }

    public Place getPlace(double lat, double lon) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLACE, new String[] { KEY_PNAME,
        KEY_ADDRESS, KEY_LATITUDE, KEY_LONGITUDE, KEY_CITY,
        KEY_FILTER }, KEY_LATITUDE + " =? AND " + KEY_LONGITUDE + " =?",
        new String[] { String.valueOf(lat), String.valueOf(lon) }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        Place place = new Place(cursor.getString(0), cursor.getString(1), 
            Double.parseDouble(cursor.getString(2)), Double.parseDouble(cursor.getString(3)),
            cursor.getString(4), cursor.getString(5));
        // return place
        return place;
    }

    public PlacePic getPlacePic(double lat, double lon, String imgfile) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLACEPICS, new String[] { KEY_LATITUDE,
        KEY_LONGITUDE, KEY_USERNAME }, KEY_LATITUDE + " =? AND " + KEY_LONGITUDE
        + " =? AND " + KEY_IMG + " =?",
        new String[] { lat, lon, imgfile }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        PlacePic placepic = new PlacePic(Double.parseDouble(cursor.getString(0)),
            Double.parseDouble(cursor.getString(1)), cursor.getString(2));
        // return placepic
        return placepic;
    }

    public PlaceList getPlaceList(double lat, double lon, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLACEPICS, new String[] { KEY_LATITUDE,
        KEY_LONGITUDE, KEY_USERNAME }, KEY_LATITUDE + " =? AND " + KEY_LONGITUDE
        + " =? AND " + KEY_USERNAME + " =?",
        new String[] { lat, lon, username }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        PlaceList placelist = new PlaceList(Double.parseDouble(cursor.getString(0)),
            Double.parseDouble(cursor.getString(1)), cursor.getString(2));
        // return placelist
        return placelist;
    }

}