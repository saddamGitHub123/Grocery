package com.backend.restapi.common;

public interface ApiErrors {
	
	// For User Message 
	public static final String ERROR__ALREADY_EXIST = "Already Username or Phone number or Email exisit";
	public static final String ERROR__EMPTY_SHOPKEEPER = "Shopkeeper List is Empty";
	public static final String ERROR__EMPTY_USER = "User List is Empty";
	public static final String ERROR__EMPTY_USER__SHOP = "Shop_ID and User_ID is empty";
	public static final String SUCCESS__SHOPKEEPER_ADD = "Successfully Add ShopKeeper";
	public static final String SUCCESS__USER_ADD = "Successfully Add User";
	public static final String SUCCESS__USER_UPDATE = "Successfully Update User";
	
	public static final String ERROR__DATA__NOT_SAVE = "Data is not save";
	
	public static final String SUCCESS__AUTHENTICATED = "Successfully Authenticated";
	
	// Push Notification 
	
	public static final String SUCCESS__SAVE_DEVICE_TOKEN = "Successfully save and update device token";
	public static final String FCM_SUCCESS__ADD_PRODUCT = " Add New Product into List ";
	public static final String FCM_SUCCESS__ADD_ORDER = " Add New Order into List : ";
	
	public static final String ERROR__DEVICE_TOKEN = "ERROR!! Server not getting Device Token ";
	
	//ORDER TABLE	
	public static final String ERROR__ORDER_LIST_EMPTY = "Orderlist is empty";
	
	
    public static final String ERROR__PHONE_CELL_MISSING = "Phone cell is missing";
    public static final String ERROR__EMAIL_MISSING = "Email Id is missing";
    public static final String ERROR__MISSING_PASSOWRD = "Password is missing";
    public static final String ERROR__MISSING_NEW_PASSOWRD = "New Password is missing";
    public static final String ERROR__MISSING_CONFIRM_PASSOWRD = "Confirm password is missing";
    public static final String ERROR__CUSTOMER_ID_MISSING = "Customer Id is missing";
    public static final String ERROR__PRODUCT_ID_MISSING = "Product Id is missing";
    public static final String ERROR__PHONE_CELL_INVALID_FORMAT = "Phone cell format is invalid";
    public static final String ERROR__PASSWORD_INVALID_FORMAT = "Password format is invalid";
    public static final String ERROR__PIN_INVALID_FORMAT = "Pin Number format is invalid";
    public static final String ERROR__EMAIL_INVALID_FORMAT = "Email Id format is invalid";
    public static final String ERROR__PASSWORD_MISMATCH = "Password and confirm password don't match";
    public static final String ERROR__CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    public static final String ERROR__URL_ALREADY_EXISTS = "Url already exists";
    public static final String ERROR__CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";
    public static final String ERROR__NO_USER_ID_EXIST = "No Customers exist";
    public static final String ERROR__NO_CONTENT_PROVIDER_EXISTS = "No Content Provider exists";
    public static final String ERROR__NO_SUPPORT_REP_EXISTS = "No Kiora Support Rep exists";
    public static final String ERROR__NO_SELECTIONS_EXIST = "Customer hasn't selected any movies";
    public static final String ERROR__UNABLE_TO_CREATE_CUSTOMER = "Unable to Create Advertisements";
    public static final String ERROR__UNABLE_TO_UPDATE_CONTENT_PROVIDER = "Unable to update Content Provider Profile";
    public static final String ERROR__UNABLE_TO_CREATE_ADVERTISEMENTS= "Unable to Create Customer";
    public static final String ERROR__UNABLE_TO_UPDATE_PASSWORD = "Unable to change the password for the Customer";
    public static final String ERROR_AUTHORIZATION_FAILED = "The authorization failed, phone cell or password may be invalid";
    public static final String ERROR_INVALID_PASSWORD = "The current password doesn't match";
    public static final String ERROR__UNABLE_TO_UPDATE_CONTENT = "Unable to update the metadata of the content";
    public static final String ERRORE_STATUS_MESSAGE = "Invalid_Credentials";

    public static final String ERROR__PRODUCT_DOES_NOT_EXIST = "Product does not exist";
    public static final String ERROR__UUID_DOES_NOT_EXIST_BACKEND = "UUID does not exist on the backend server";

    public static final String ERROR__CARD_NUMBER_MISSING = "Credit Card Number is missing";
    public static final String ERROR__CARD_NUMBER_INVALID_FORMAT = "Credit Card Number format is invalid";
    public static final String ERROR__CVV_MISSING = "CVV is missing";
    public static final String ERROR__CVV_INVALID_FORMAT = "CVV format is invalid";
    public static final String ERROR__NAME_MISSING = "Card Holder Name is missing";
    public static final String ERROR__EXPIRY_MISSING = "Card Expiry Date is missing";
    public static final String ERROR__EXPIRY_INVALID_FORMAT = "Expiry Date format is invalid";

    public static final String SUCCESS__AUTHORIZATION_PASS = "The authorization was successful";
    public static final String SUCCESS__REGISTRATION_PASS = "The registration was successful";
    public static final String SUCCESS__PASSWORD_RESET = "The reset password was successful";
    
    public static final String SUCCESS__CUSTOMER_EXISTS = "The Customer exists!";
    public static final String SUCCESS__PRODUCT_EXISTS = "The Product exists!";
    public static final String SUCCESS__CUSTOMER_DELETED = "The Customer is deleted successfully";
    public static final String SUCCESS__PASSWORD_UPDATED = "The password has been updated successfully";
    public static final String SUCCESS__RESET_PERSONALITIES = "The Personalitites have been reset successfully";
    
    
    public static final String ERROR__NO_CATEGORIES_EXIST = "No Categories exist";
    public static final String ERROR__NO_USER_EXIST = "No User exist";
    public static final String ERROR__NO_PRODUCTS_EXIST = "No Products exist";
    public static final String ERROR__NO_USERS_EXIST = "No Users exist";
    
    public static final String ERROR__MISSING_CATEGORY_ID = "Category Id is missing";
    public static final String ERROR__MISSING_START_INDEX = "Start Index is missing";
    public static final String ERROR__INVALID_END_INDEX = "Invalid End Index";
    public static final String ERROR__INVALID_CATEGORY = "Invalid Category";
    public static final String ERROR__INVALID_YEAR = "Year not a valid format";
    public static final String ERROR__MISSING_TITLE = "Content title is missing";
    public static final String ERROR__NO_SEARCH_CRITERIA = "No Search Criteria specified";
    public static final String ERROR__NO_TRANSACTIONS_EXIST = "No Transactions exist";
    public static final String ERROR__NO_METADATA_EXISTS = "No Metadata exists";
    public static final String ERROR__NO_SERVERS_EXIST = "No Servers / Kiosks exist";
    public static final String ERROR__NO_GENRES_EXIST = "No Genres exist";

    public static final String ERROR__STORE_REFERRER = "Unable to store the Referrer in the database";
    public static final String ERROR__NO_SERVER_IP_EXISTS = "Unable to get the IP Address assigned to the server";

    public static final String SUCCESS__VALID_SUBSCRIBER = "Valid Kiora Subscriber";
    public static final String SUCCESS__LICENSE_GEN = "Successfully generated the license";
    public static final String SUCCESS__LOGIN_STATUS = "Successfully Login";

    public static final String ERROR__INVALID_OPERATOR = "Not a valid operator";
    public static final String ERROR__INVALID_VIDEOCON_RTN = "Not a valid Videocon RTN";
    public static final String ERROR__PAYMENT_PROCESS = "Invalid Redeem Pin";
    public static final String ERROR__LOG_EVENT = "Unable to log Event Statistics";
    public static final String ERROR__LOG_TRANSACTION = "Unable to log the transaction";
    public static final String ERROR__NO_CSSN_EXISTS = "No CSSN found for the subscriber";
    public static final String ERROR__ASSIGN_PERSONALITY = "Unable to assign a personality to the Subscriber's CSSN";
    public static final String ERROR__ASSIGN_HOTSPOT_PERSONALITY = "Unable to assign a personality to the UUID";
    public static final String ERROR__GET_PERSONALITY = "Unable to get the personality assigned to the Subscriber's CSSN";
    public static final String ERROR__GET_HOTSPOT_PERSONALITY = "Unable to get the personality assigned for the UUID";
    public static final String ERROR__GET_LICENSE = "Unable to get the content license";
    public static final String ERROR__RESET_PERSONALITIES = "Unable to reset personalities assigned to the Subscribers CSSNs";
    public static final String ERROR__RESET_DEVICE_PERSONALITIES = "Unable to reset personalities assigned to the Devices";

    public static final String ERROR__NO_CONTENT_REVIEWS_EXIST = "No reviews exist for the selected content";
    public static final String SUCCESS__CONTENT_REVIEWS_EXIST = "Got reviews for the selected content";

    public static final String ERROR__NO_IP_TO_TRACK = "No IP Address to track bandwidth";
    public static final String ERROR__NO_IP_TRACKED = "No IP Address tracked for reporting";

    public static final String ERROR__NO_ADMIN = "Unable to get admin details";
    public static final String ERROR__ADMIN_SIGN_UP = "Unable to sign-up the admin";
    public static final String ERROR__NO_LOCATION = "Unable to get the location details";
    public static final String ERROR__ADD_CUSTOMER = "Unable to add the customer";
    public static final String ERROR__NO_CUSTOMER = "Unable to get checked-in customer details";
    public static final String ERROR__UPDATE_PHONE_NUMBER = "Unable to update the phone number of the checked-in customer";
    public static final String ERROR__ADD_TRANSACTION = "Unable to add the transaction for the customer";
    public static final String ERROR__NO_TRANSACTION = "No transactions found for the customer";
    public static final String ERROR__NO_ROOM_SERVER_MAPPING = "No Room Number mapped to the Kiora Appliance Id";
    public static final String ERROR__CHECK_OUT = "Unable to Check-out the customer";
    public static final String ERROR__ADD_ROOM_SERVER_MAPPING = "Unable to add the Kiora Appliance mapping to the Room Number";
    public static final String ERROR__NO_MAPPINGS = "No Room Numbers mapped to the Kiora Appliance Ids";
    public static final String ERROR__CHECK_IN_NO_APPLIANCE = "No Kiora Appliance mapped to the Room Number. Check-In not allowed!!";
    public static final String ERROR__CHECK_IN_NOT_VACANT = "The room is not vacant!!";
}
