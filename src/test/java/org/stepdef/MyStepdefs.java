package org.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.ResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import io.restassured.path.json.JsonPath;

import java.util.*;

public class MyStepdefs {
    RequestSpecification hRequest;
    Response hResponse;
    String Username;
    String Password;

    @Given("^I create a request \"([^\"]*)\"$")
    public void i_create_a_request(String URL) throws Throwable {
        RestAssured.baseURI = URL;
        //Get Request Specification of request that we want to sent to server
        //Server Request point is specified in baseURI
        hRequest = RestAssured.given();


    }

    //Add Header to request and send Get method to server
    @When("^I send request with body to server \"([^\"]*)\"$")
    public void i_send_request_with_body_to_server(String request) throws Throwable{
        Header h = new Header("Content-Type","application/json");
        System.out.println("Header:"+ hRequest.toString());
        hResponse = hRequest.header(h).request(Method.GET,request);
       // hRequest.header(h).request(Method.GET,"/employees");
    }

    @Then("^I validate response parameters$")
    public void i_validate_response_parameters(){
       // hResponse = RestAssured.given().header(new Header("Content-Type","application/json")).request(Method.GET,"/Hyderabad")
            //ResponseBody responseBody = hResponse.getBody();
        System.out.println("Statusciode:" +hResponse.getStatusCode());
            System.out.println("Status Line : " + hResponse.getStatusLine());
            ResponseBody responseB = hResponse.getBody();
            //prints the complete body in console
            System.out.println("Response Body : " + responseB.prettyPrint());
            //reference is printed
            System.out.println("Response String : "+responseB.toString());

               //Returns complete status Line
           String statusLine = hResponse.getStatusLine();
            System.out.println("StatusLine : " + statusLine);
            Assert.assertEquals("Expected Status Line","HTTP/1.1 200 OK",statusLine);

               //Getting Header
            String Header = hResponse.getHeader("Content-Type");
            System.out.println("Content-Type : " + Header);
            Assert.assertEquals("Content-Type" ,"application/json",Header);

            //Getting multiple Headers
        Headers headers = hResponse.getHeaders();
        System.out.println("Length of Headers : "+ headers.size());
        Iterator i = headers.iterator();
        System.out.println("List of Headers");
        while(i.hasNext())
        {
            System.out.println("Next:"+i.next());
        }
        System.out.println("Get Header Value :"+headers.getValue("Connection"));
    }

    @Then("^I validate response body using jsonpath$")
    public void iValidateResponseBodyUsingJsonpath() {
        JsonPath respJsonp = hResponse.jsonPath();
        System.out.println("WindSpeed : " + respJsonp.getString("WindSpeed"));

        //System.out.println("City:"+respJsonp.getString("City"));
        //Ask murthy about using below 3 methods in JsonPath class
        /*respJsonp.getList()
        respJsonp.getJsonObject()
        respJsonp.get()   */
    }

    @When("^I send post request with body to server \"([^\"]*)\"$")
    public void iSendPostRequestWithBodyToServer(String post1) throws Throwable {
        Header h = new Header("Content-Type","application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("name","Jack");
        requestParams.put("occupation","programmer");
        //requestParams.put("age","23");

        // this toJSONString method converts params in {param1,param2,param3} format
        // we can directly mention in JSON String format , but the issue while using Numeric / float values as params
        hResponse = hRequest.header(h).body(requestParams.toJSONString()).post(post1);
    }

    @Then("^I validate response Status Code (\\d+)$")
    public void iValidateResponseStatusCode(int statuscode) {
        String responseBody = hResponse.getBody().toString();
        int Status = hResponse.getStatusCode();
        System.out.println("Status Code : " + Status);
        System.out.println("Response:"+ hResponse.toString());
        Assert.assertEquals("Success Status code returned",statuscode,Status);
        // System.out.println("Response : "+ responseBody);
    }

    @Then("^I validate post req response body$")
    public void iValidatePostReqResponseBody() {
        JsonPath respJsonp = hResponse.jsonPath();
        //System.out.println("Response: "+respJsonp.prettyPrint());
        /*String URL = hResponse.jsonPath().getString("origin");
        System.out.println("URL: "+ URL );*/
      /*  Map<Object, Object> Response_details = hResponse.jsonPath().getMap("headers");
        System.out.println("List :" + Response_details.get(0));*/
        Map<String,String> headers1 = hResponse.jsonPath().get("headers");
        System.out.println("headers : " + headers1.get("Host"));
    }

    @When("^I send post request with body to server \"([^\"]*)\" using params$")
    public void iSendPostRequestWithBodyToServerUsingParams(String post1) throws Throwable {
        Header h = new Header("Content-Type","application/json");
        hResponse = hRequest.header(h).param("name","Jack").param("occupation","programmer").post(post1);
                //request(Method.POST,post1);
                //post(post1);
    }


    @Then("^I validate response body using jsonpath_example$")
    public void iValidateResponseBodyUsingJsonpath_example() {
        JsonPath respJsonp = hResponse.jsonPath();
        List Jarray = respJsonp.get();
                //respJsonp.get("");
                //respJsonp.get();
        Iterator i = Jarray.iterator();
        System.out.println();
        while(i.hasNext())
        {
            HashMap<String,String> employee = (HashMap<String, String>) i.next();
            Set keys = employee.keySet();
            Iterator itr = keys.iterator();
            while(itr.hasNext()) {
                String key = (String) itr.next();
                System.out.print(key + " : " + employee.get(key)+"\t");
            }
            System.out.println();
        }

    }

    @When("^I send post request with body to server \"([^\"]*)\" using parameters$")
    public void iSendPostRequestWithBodyToServerUsingParameters(String post1) throws Throwable {
        Header h = new Header("Content-Type","application/json");
        Map<String,String> employee = new HashMap<String, String>();
        employee.put("id","156380");
        employee.put("employee_name","laboriosam");
        employee.put("employee_salary","0");
        employee.put("employee_age","0");
        employee.put("profile_image","");
        hResponse = hRequest.header(h).param("id","156385")
                .param("employee_name","laboriosam")
                .param("employee_salary","0")
                .param("employee_age","0").param("profile_image","").post(post1);
    }

    @When("^I send post request with body to server \"([^\"]*)\" using param$")
    public void iSendPostRequestWithBodyToServerUsingParam(String weather){
            Header h = new Header("Content-Type","application/json");
            hResponse = hRequest.header(h)
                    .param("q","London,uk")
                    .param("APPID","9496a86bc5c4931b83d66a9b3fea0c8e")
                    .post(weather);
    }

    @Then("^I validate post req response$")
    public void iValidatePostReqResponse() {
       System.out.println("Expected Status : 200");
       System.out.println("Status Code : " + hResponse.getStatusCode());
       System.out.println("Response Content : "+ hResponse.prettyPrint());
    }

    @When("^I send Authorization PUT request with body \"([^\"]*)\" with employee id (\\d+)$")    public void iSendAuthorizationPUTRequestWithBodyWithEmployeeId(String URL, int empid) throws Throwable {
     JSONObject requestParams = new JSONObject();
     requestParams.put("name","Sunitha");
     requestParams.put("age","25");
     requestParams.put("salary","25000");

     hRequest.body(requestParams.toJSONString());
     hResponse = hRequest.put(URL+empid);
    }

    @When("^I send credentials to check the for Authorization$")
    public void iSendCredentialsToCheckTheForAuthorization() {
        credentials();
        //hRequest



    }

    private void credentials()
    {
        Username = "ToolsQA";
        Password = "TestPassword";
    }
}
