package com.mehfils.music.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mehfils.music.entities.Users;
import com.mehfils.music.services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {

	@Autowired
	UserService userv;

	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_gntuxBajgoHjKF", "6WEU7UxV2sfL4tvext6DJYfm");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", 50000);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1", "Tea, Earl Grey, Hot");
			orderRequest.put("notes", notes);

			order = razorpay.orders.create(orderRequest);
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order.toString();
	}

	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
			@RequestParam String signature) {
		try {

			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_gntuxBajgoHjKF", "6WEU7UxV2sfL4tvext6DJYfm");

			String verificationData = orderId + "|" + paymentId;

			boolean isValidSignature = Utils.verifySignature(verificationData, signature, "6WEU7UxV2sfL4tvext6DJYfm");

			return isValidSignature;
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) {

		String email = (String) session.getAttribute("email");
		Users user = userv.getUser(email);
		user.setPremium(true);
		userv.updateUser(user);
		return "login";
	}

	@GetMapping("payment-failure")
	public String paymentFailure() {
		return "login";
	}

}
