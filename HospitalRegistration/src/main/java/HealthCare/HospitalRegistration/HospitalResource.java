// Guide ---------------> This is HospitalResource.java (Resource) file Hospital Registration function of HealthCare System PAF.

package HealthCare.HospitalRegistration;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;*/

import HealthCare.HospitalRegistration.Hospital;

// Guide ---------------> Under this line HospitalResource class.

@Path("/Hospital")
public class HospitalResource {

// Guide ---------------> Under this line create hospital object.
	
	Hospital hospitalObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital() {
		//return "Hello";
		return hospitalObj.readHospital();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hoptName") String hoptName,
			@FormParam("hoptAddress") String hoptAddress,
			@FormParam("hoptPNumber") String hoptPNumber,
			@FormParam("hoptCharge") String hoptCharge)
	{
	String output = hospitalObj.insertHospital(hoptName, hoptAddress, hoptPNumber, hoptCharge, 0);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitals(String hospitalData)
	{
		
// Guide ---------------> Under this line convert the input a object.
	
	JsonObject hospitalObjt = new JsonParser().parse(hospitalData).getAsJsonObject();
	
// Guide ---------------> Under this line read the values from the object.
	
	String hoptId = hospitalObjt.get("hoptId").getAsString();
	String hoptName = hospitalObjt.get("hoptName").getAsString();
	String hoptAddress = hospitalObjt.get("hoptAddress").getAsString();
	String hoptPNumber = hospitalObjt.get("hoptPNumber").getAsString();
	String hoptCharge = hospitalObjt.get("hoptCharge").getAsFtring();
	String output = hospitalObjt.updateHospitals(hoptId, hoptName, hoptAddress, hoptPNumber, hoptCharge);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData)
	{
		
// Guide ---------------> Under this line convert the input an XML document.
	
	Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
	
// Guide ---------------> Under this line read the value "hoptId" element.

	String hoptId = doc.select("hoptId").text();
	String output = hospitalObj.deleteHospital(hoptId);
	return output;
	}
		
}

// Guide ---------------> End of the HospitalResource.java (Resource) file.