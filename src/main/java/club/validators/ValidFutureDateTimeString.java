package club.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("club.validators.ValidFutureDateTimeString")
public class ValidFutureDateTimeString implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		//TODO: since we send date as an string from client and in backend parse it we do not have to worry about client-side
		// time zone. Different platform might have timezone issues if "now" uses another timezone than date-parser for example.
		// Lets fix this when/if we have to.
		
		String date = value.toString();
		Date now = new Date();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat.setLenient(false);
			
			Date rdate = dateFormat.parse(date);
			
			if(rdate.before(now)) throw new ValidatorException(new FacesMessage("Not a valid future date time"));
		} catch (ParseException e) {
			//e.printStackTrace(); //no reason to log something that could "easy" be done by end user.
			throw new ValidatorException(new FacesMessage("Not a valid future date time"));
		}
		
	}

}
