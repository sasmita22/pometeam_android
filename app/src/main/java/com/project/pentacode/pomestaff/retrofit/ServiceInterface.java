package com.project.pentacode.pomestaff.retrofit;

import com.project.pentacode.pomestaff.model.LoginUser;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.model.Task;
import com.project.pentacode.pomestaff.model.ViewProject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginUser> doLogin(@Field("email") String email,
                            @Field("password") String password);

    @GET("projects/{nip}/")
    Call<List<Project>> getProjectList(@Path("nip") String nip, @Header("Authorization") String authorization);

    @GET("project/{id_project}")
    Call<Project> getProjectDetail(@Path("id_project") int id_project,@Header("Authorization") String authorization);

    @GET("staff/{nip}")
    Call<Staff> getStaff(@Path("nip") String nip,@Header("Authorization") String authorization);

    @GET("project/getHirarkiStep/{id_project}")
    Call<List<Step>> getSteps(@Path("id_project") int idProject, @Header("Authorization") String authorization);

    @GET("step/project/{id_project}/step/{id_step}")
    Call<Step> getStep(@Path("id_project") int idProject, @Path("id_step") int idStep, @Header("Authorization") String authorization);

    @GET("tasks/project/{id_project}/step/{id_step}")
    Call<List<Task>> getTasks(@Path("id_project") int idProject, @Path("id_step") int idStep, @Header("Authorization") String authorization);

    @GET("task/{id_task}")
    Call<Task> getTask(@Path("id_task") int idTask, @Header("Authorization") String authorization);

    @GET("staff/getStaffForLeaderOrTeam/{id_project}")
    Call<List<Staff>> getStaffForLeaderOrTeam(@Path("id_project") int idProject, @Header("Authorization") String authorization);

    @FormUrlEncoded
    @PATCH("project/setLeaderProjectStructure/{id_project}/{id_step}/")
    Call<ViewProject> setNewLeader(@Field("leader") String idNewLeader, @Path("id_project") int idProject, @Path("id_step") int idStep, @Header("Authorization") String authorization);

    @GET("task/penanggungJawabTask/project/{id_project}/step/{id_step}")
    Call<List<Staff>> getPenangguJawabTask(@Path("id_project") int idProject, @Path("id_step") int idStep, @Header("Authorization") String authorization);
}
