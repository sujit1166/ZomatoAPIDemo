package com.sujit.zomatoapidemo.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantItem implements Parcelable {

    @SerializedName("R")
    @Expose
    private R r;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("switch_to_order_menu")
    @Expose
    private int switchToOrderMenu;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("timings")
    @Expose
    private String timings;
    @SerializedName("average_cost_for_two")
    @Expose
    private int averageCostForTwo;
    @SerializedName("price_range")
    @Expose
    private int priceRange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("highlights")
    @Expose
    private List<String> highlights;
    @SerializedName("opentable_support")
    @Expose
    private int opentableSupport;
    @SerializedName("is_zomato_book_res")
    @Expose
    private int isZomatoBookRes;
    @SerializedName("mezzo_provider")
    @Expose
    private String mezzoProvider;
    @SerializedName("is_book_form_web_view")
    @Expose
    private int isBookFormWebView;
    @SerializedName("book_form_web_view_url")
    @Expose
    private String bookFormWebViewUrl;
    @SerializedName("book_again_url")
    @Expose
    private String bookAgainUrl;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("all_reviews_count")
    @Expose
    private int allReviewsCount;
    @SerializedName("photos_url")
    @Expose
    private String photosUrl;
    @SerializedName("photo_count")
    @Expose
    private int photoCount;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("menu_url")
    @Expose
    private String menuUrl;

    @SerializedName("featured_image")
    @Expose
    private String featuredImage;

//    @SerializedName("medio_provider")
//    @Expose
//    private int medioProvider;
    @SerializedName("has_online_delivery")
    @Expose
    private int hasOnlineDelivery;

    @SerializedName("is_delivering_now")
    @Expose
    private int isDeliveringNow;
    @SerializedName("include_bogo_offers")
    @Expose
    private boolean includeBogoOffers;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("is_table_reservation_supported")
    @Expose
    private int isTableReservationSupported;
    @SerializedName("has_table_booking")
    @Expose
    private int hasTableBooking;
    @SerializedName("book_url")
    @Expose
    private String bookUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("all_reviews")
    @Expose
    private AllReviews allReviews;
    @SerializedName("establishment")
    @Expose
    private List<String> establishment = null;

    protected RestaurantItem(Parcel in) {
        apikey = in.readString();
        id = in.readString();
        name = in.readString();
        url = in.readString();
        switchToOrderMenu = in.readInt();
        cuisines = in.readString();
        timings = in.readString();
        averageCostForTwo = in.readInt();
        priceRange = in.readInt();
        currency = in.readString();
        highlights = in.createStringArrayList();
        opentableSupport = in.readInt();
        isZomatoBookRes = in.readInt();
        mezzoProvider = in.readString();
        isBookFormWebView = in.readInt();
        bookFormWebViewUrl = in.readString();
        bookAgainUrl = in.readString();
        thumb = in.readString();
        allReviewsCount = in.readInt();
        photosUrl = in.readString();
        photoCount = in.readInt();
        menuUrl = in.readString();
        featuredImage = in.readString();
        hasOnlineDelivery = in.readInt();
        isDeliveringNow = in.readInt();
        includeBogoOffers = in.readByte() != 0;
        deeplink = in.readString();
        isTableReservationSupported = in.readInt();
        hasTableBooking = in.readInt();
        bookUrl = in.readString();
        eventsUrl = in.readString();
        phoneNumbers = in.readString();
        establishment = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apikey);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeInt(switchToOrderMenu);
        dest.writeString(cuisines);
        dest.writeString(timings);
        dest.writeInt(averageCostForTwo);
        dest.writeInt(priceRange);
        dest.writeString(currency);
        dest.writeStringList(highlights);
        dest.writeInt(opentableSupport);
        dest.writeInt(isZomatoBookRes);
        dest.writeString(mezzoProvider);
        dest.writeInt(isBookFormWebView);
        dest.writeString(bookFormWebViewUrl);
        dest.writeString(bookAgainUrl);
        dest.writeString(thumb);
        dest.writeInt(allReviewsCount);
        dest.writeString(photosUrl);
        dest.writeInt(photoCount);
        dest.writeString(menuUrl);
        dest.writeString(featuredImage);
        dest.writeInt(hasOnlineDelivery);
        dest.writeInt(isDeliveringNow);
        dest.writeByte((byte) (includeBogoOffers ? 1 : 0));
        dest.writeString(deeplink);
        dest.writeInt(isTableReservationSupported);
        dest.writeInt(hasTableBooking);
        dest.writeString(bookUrl);
        dest.writeString(eventsUrl);
        dest.writeString(phoneNumbers);
        dest.writeStringList(establishment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantItem> CREATOR = new Creator<RestaurantItem>() {
        @Override
        public RestaurantItem createFromParcel(Parcel in) {
            return new RestaurantItem(in);
        }

        @Override
        public RestaurantItem[] newArray(int size) {
            return new RestaurantItem[size];
        }
    };


    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public void setSwitchToOrderMenu(int switchToOrderMenu) {
        this.switchToOrderMenu = switchToOrderMenu;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public int getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(int averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public int getOpentableSupport() {
        return opentableSupport;
    }

    public void setOpentableSupport(int opentableSupport) {
        this.opentableSupport = opentableSupport;
    }

    public int getIsZomatoBookRes() {
        return isZomatoBookRes;
    }

    public void setIsZomatoBookRes(int isZomatoBookRes) {
        this.isZomatoBookRes = isZomatoBookRes;
    }

    public String getMezzoProvider() {
        return mezzoProvider;
    }

    public void setMezzoProvider(String mezzoProvider) {
        this.mezzoProvider = mezzoProvider;
    }

    public int getIsBookFormWebView() {
        return isBookFormWebView;
    }

    public void setIsBookFormWebView(int isBookFormWebView) {
        this.isBookFormWebView = isBookFormWebView;
    }

    public String getBookFormWebViewUrl() {
        return bookFormWebViewUrl;
    }

    public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
        this.bookFormWebViewUrl = bookFormWebViewUrl;
    }

    public String getBookAgainUrl() {
        return bookAgainUrl;
    }

    public void setBookAgainUrl(String bookAgainUrl) {
        this.bookAgainUrl = bookAgainUrl;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public int getAllReviewsCount() {
        return allReviewsCount;
    }

    public void setAllReviewsCount(int allReviewsCount) {
        this.allReviewsCount = allReviewsCount;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public int getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(int hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public int getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(int isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public boolean isIncludeBogoOffers() {
        return includeBogoOffers;
    }

    public void setIncludeBogoOffers(boolean includeBogoOffers) {
        this.includeBogoOffers = includeBogoOffers;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public int getIsTableReservationSupported() {
        return isTableReservationSupported;
    }

    public void setIsTableReservationSupported(int isTableReservationSupported) {
        this.isTableReservationSupported = isTableReservationSupported;
    }

    public int getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(int hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public AllReviews getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(AllReviews allReviews) {
        this.allReviews = allReviews;
    }

    public List<String> getEstablishment() {
        return establishment;
    }

    public void setEstablishment(List<String> establishment) {
        this.establishment = establishment;
    }

    public static Creator<RestaurantItem> getCREATOR() {
        return CREATOR;
    }
}