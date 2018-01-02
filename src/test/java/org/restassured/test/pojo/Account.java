package org.restassured.test.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("protected")
    private Boolean _protected;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("always_use_https")
    private Boolean alwaysUseHttps;
    @JsonProperty("use_cookie_personalization")
    private Boolean useCookiePersonalization;
    @JsonProperty("sleep_time")
    private SleepTime sleepTime;
    @JsonProperty("geo_enabled")
    private Boolean geoEnabled;
    @JsonProperty("language")
    private String language;
    @JsonProperty("discoverable_by_email")
    private Boolean discoverableByEmail;
    @JsonProperty("discoverable_by_mobile_phone")
    private Boolean discoverableByMobilePhone;
    @JsonProperty("display_sensitive_media")
    private Boolean displaySensitiveMedia;
    @JsonProperty("allow_contributor_request")
    private String allowContributorRequest;
    @JsonProperty("allow_dms_from")
    private String allowDmsFrom;
    @JsonProperty("allow_dm_groups_from")
    private String allowDmGroupsFrom;
    @JsonProperty("translator_type")
    private String translatorType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean get_protected() {
        return _protected;
    }

    public void set_protected(Boolean _protected) {
        this._protected = _protected;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Boolean getAlwaysUseHttps() {
        return alwaysUseHttps;
    }

    public void setAlwaysUseHttps(Boolean alwaysUseHttps) {
        this.alwaysUseHttps = alwaysUseHttps;
    }

    public Boolean getUseCookiePersonalization() {
        return useCookiePersonalization;
    }

    public void setUseCookiePersonalization(Boolean useCookiePersonalization) {
        this.useCookiePersonalization = useCookiePersonalization;
    }

    public SleepTime getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(SleepTime sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getDiscoverableByEmail() {
        return discoverableByEmail;
    }

    public void setDiscoverableByEmail(Boolean discoverableByEmail) {
        this.discoverableByEmail = discoverableByEmail;
    }

    public Boolean getDiscoverableByMobilePhone() {
        return discoverableByMobilePhone;
    }

    public void setDiscoverableByMobilePhone(Boolean discoverableByMobilePhone) {
        this.discoverableByMobilePhone = discoverableByMobilePhone;
    }

    public Boolean getDisplaySensitiveMedia() {
        return displaySensitiveMedia;
    }

    public void setDisplaySensitiveMedia(Boolean displaySensitiveMedia) {
        this.displaySensitiveMedia = displaySensitiveMedia;
    }

    public String getAllowContributorRequest() {
        return allowContributorRequest;
    }

    public void setAllowContributorRequest(String allowContributorRequest) {
        this.allowContributorRequest = allowContributorRequest;
    }

    public String getAllowDmsFrom() {
        return allowDmsFrom;
    }

    public void setAllowDmsFrom(String allowDmsFrom) {
        this.allowDmsFrom = allowDmsFrom;
    }

    public String getAllowDmGroupsFrom() {
        return allowDmGroupsFrom;
    }

    public void setAllowDmGroupsFrom(String allowDmGroupsFrom) {
        this.allowDmGroupsFrom = allowDmGroupsFrom;
    }

    public String getTranslatorType() {
        return translatorType;
    }

    public void setTranslatorType(String translatorType) {
        this.translatorType = translatorType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}