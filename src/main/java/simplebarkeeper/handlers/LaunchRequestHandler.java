package simplebarkeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

/**
 * Class that handles the Launch Request. It also implements any first time
 * initialization of DataBases. Furthermore it welcomes the User.
 * 
 * @author Robin Grellner
 *
 */
public class LaunchRequestHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	/**
	 * {@inheritDoc}} The Method handles everything that needs to happen at the
	 * Launch of the Skill and welcomes the user.
	 */
	@Override
	public Optional<Response> handle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		sessionAttributes.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_DEFAULT);
		sessionAttributes.put(States.GET_RECIPE_STATE_KEY, States.GET_RECIPE_DEFAULT);
		attributesManager.setSessionAttributes(sessionAttributes);

		String welcome = "Dein Barkeeper heißt dich herzlich Willkommen! Wie kann ich dir behilflich sein? ";
		String repromptMessage = "Falls du Hilfe brauchen solltest, sag einfach: Hilf mir";

		return input.getResponseBuilder().withSpeech(welcome).withReprompt(repromptMessage).build();
	}

}
