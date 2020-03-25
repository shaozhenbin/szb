package com.szb.controller;

import static java.util.Arrays.asList;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.szb.mongo.oauth.MongoApprovalStore;
import com.szb.mongo.oauth.MongoClientDetailsService;
import com.szb.mongo.oauth.MongoTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private MongoClientDetailsService clientDetailsService;
	@Autowired
	private MongoApprovalStore approvalStore;
	@Autowired
	private MongoTokenStore tokenStore;

	private final static String REDIRECT_APPROVAL_LIST = "redirect:/approval/list";

	@RequestMapping("/list")
	public String getList(Model model, Principal principal) {
		List<Approval> approvals = clientDetailsService
				.listClientDetails()
				.stream()
				.map(clientDetail -> approvalStore.getApprovals(
						principal.getName(), clientDetail.getClientId()))
						.flatMap(Collection::stream).collect(Collectors.toList());
		model.addAttribute("approvals", approvals);
		return "approvals";
	}

	@RequestMapping(value = "/revoke", method = RequestMethod.POST)
	public String postRevoke(@ModelAttribute Approval approval) {
		approvalStore.revokeApprovals(asList(approval));
		tokenStore
		.findTokensByClientIdAndUserName(approval.getClientId(), approval.getUserId())
		.forEach(tokenStore::removeAccessToken);
		return REDIRECT_APPROVAL_LIST;
	}

}
