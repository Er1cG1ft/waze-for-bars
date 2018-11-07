package Baze;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;

@Controller
public class HomeController {

	@RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyC9BQHyDExP8RYI5kBJmWq5ud0O2V5bsVc")
			    .build();

		LatLng coordinates = new LatLng(42.360081, -71.058884);
		try {
			PlacesSearchResponse bars = PlacesApi.nearbySearchQuery(context, coordinates)
				.radius(5000)
				.type(PlaceType.BAR)
				.await();
			String nextPageToken = bars.nextPageToken;
			for (int i=0; i < bars.results.length; i++) {
				System.out.println(bars.results[i].toString());
			}
			mav.addObject("bars", bars.results);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
    	return mav;
    }
}
