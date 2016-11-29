package com.abhishek.interiit2016.utils;

import com.abhishek.interiit2016.model.APIResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by GUNDA ABHISHEK on 11-05-2016.
 */
public interface DataService {
    @GET(APIConstants.API_GET_POINTS_TABLE)
    public void getEvents(
            @Query("gender") String gen, Callback<APIResponse> cb
    );
    @GET(APIConstants.API_GET_SCHDULE)
    public void geteventschedule(
            @Query("sport") String sport,@Query("gender") String gender,@Query("day") String day, Callback<APIResponse> cb
    );
    @GET(APIConstants.API_GET_RESULTS)
    public void getresults(
            @Query("sport") String sport,@Query("gender") String gender,@Query("day") String day, Callback<APIResponse> cb
    );
    @GET(APIConstants.API_GET_IMAGES)
    public void getimages(
            @Query("sport") String sport,@Query("gender")  String gender, Callback<APIResponse> cb
    );
    @GET(APIConstants.API_GET_IMAGES)
    public void getgalleryimages(
            @Query("sport") String sport, Callback<APIResponse> cb
    );
    /*@GET(APIConstants.API_ANALYTICS_DATA)
    public void getStatisticsOfEvent(
            @Query("enddate") String ed, @Query("eventids") String idarray, @Query("startdate") String sd, Callback<APIResponse> cb
    );

    @GET(APIConstants.API_LOGIN)
    public void getLoginDetails(
            @Query("emailId") String email, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_ATTENDEES_PAGINATION)
    public void getAttendeeList(
            @Query("start") String starttime, @Query("end") String endtime, @Query("eventId") String id, @Query("status") String status, @Query("startIndex") String strtindex, @Query("maxResults") String maximumres, @Query("ticketIds") String tickets, Callback<APIResponse> cb
    );

    @GET(APIConstants.API_USER_TICKETS)
    public void getAllTicketIds(
            @Query("id") String eventid, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_UPDATE_GCM_TOKEN)
    public void updateGcmId(
            @Query("userid") Integer id, @Query("gcmid") String gcmtoken, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_DELETE_GCM_TOKEN)
    public void deleteGcmId(
            @Query("userid") Integer id, Callback<APIResponse> cb
    );

    @GET(APIConstants.API_SEARCH_ATTENDEES)
    public void getSearcheddata(
            @Query("searchParam") String param, @Query("eventId") String id, Callback<APIResponse> cb
    );

    @GET(APIConstants.API_GET_NOTIFICATION_PREFS)
    public void getnotificationpref(
            @Query("userid") String userId, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_POST_NOTIFICATION_PREFS)
    public void postnotificationpref(
            @Query("userid") String userId, @Query("preference") String pref, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_POST_CHECKIN)
    public void updatecheck(
            @Query("registrationId") String userId, @Query("mark") String status, Callback<APIResponse> cb
    );

    @POST(APIConstants.API_NEW_ATTENDEES_PAGINATION)
    public void getnewAttendeeList(
            @Query("start") String starttime, @Query("end") String endtime, @Query("eventId") String id, @Query("status") String status, @Query("startIndex") String strtindex, @Query("maxResults") String maximumres, @Query("ticketIds") String tickets, @Query("checkInStatus") String checkstatus, Callback<APIResponse> cb
    );
*/
}
