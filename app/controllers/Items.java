package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Item;
import models.Shop;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;

public class Items extends Controller {
	
	public class CreateItem {
		public String name;
		public Double price;
	}
	
	static final Shop shop = new Shop(); // Refer to your shop implementation
	public static Result list(Integer page) {
		return ok(Json.toJson(shop.list()));
	}
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create() {
		JsonNode json = request().body().asJson();
		CreateItem createItem;
		try {
		createItem = Json.fromJson(json, CreateItem.class);
		} catch(RuntimeException e) {
		return badRequest();
		}
		Item item = shop.create(createItem.name, createItem.price);
		if (item != null) {
		return ok(Json.toJson(item));
		} else {
		return internalServerError();
		}
	}
	public static Result details(Long id) {
		Item item = shop.get(id);
		if (item != null) {
			return ok(Json.toJson(item));
		} else {
		return notFound();
		}
	}
	public static Result update(Long id) {
		return status(NOT_IMPLEMENTED);
	}
	public static Result delete(Long id) {
		return status(NOT_IMPLEMENTED);
	}
}
