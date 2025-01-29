# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions


# This is a simple example for a custom action which utters "Hello World!"

from typing import Any, Text, Dict, List

from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher


# class ActionHelloWorld(Action):
#
#     def name(self) -> Text:
#         return "action_hello_world"
#
#     def run(self, dispatcher: CollectingDispatcher,
#             tracker: Tracker,
#             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#
#         dispatcher.utter_message(text="Hello World!")
#         return []

class ExtractFoodEntity(Action):
    def name(self) -> Text:
        return "extract_food_entity"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        food_entity = next(tracker.get_latest_entity_values("food"), None)

        if food_entity:
            dispatcher.utter_message(text=f'You have selected "{food_entity}"')
        else:
            dispatcher.utter_message(text="I'm sorry, I could not detect food choice")

        return []

class OrderFoodAction(Action):
    def name(self) -> Text:
        return "order_food_action"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        dispatcher.utter_message(text="Sure, Which kind of food would you like to order?")

        return []

class ConfirmOrderAction(Action):
    def name(self) -> Text:
        return "confirm_order_action"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        food_entity = next(tracker.get_latest_entity_values("food"), None)

        if food_entity:
            dispatcher.utter_message(text=f'I have ordered "{food_entity} for you"')
        else:
            dispatcher.utter_message(text="I'm sorry, I could not detect food choice")

        return []
