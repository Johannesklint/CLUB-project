package club.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("club.validators.ValidDateString")
public class ValidDateString implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {		
		String date = value.toString();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);
		} catch (ParseException e) {
			//e.printStackTrace(); //no reason to log something that could "easy" be done by end user.
			throw new ValidatorException(new FacesMessage("Not a valid date"));
		}

	}

}
