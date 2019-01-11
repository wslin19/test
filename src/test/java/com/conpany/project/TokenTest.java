package com.conpany.project;

import com.company.project.utils.TokenUtils;

public class TokenTest
{
	public static void main(String[] args)
	{
		String token = TokenUtils.getToken(1 + "");
		System.out.println(token);
	}
}
