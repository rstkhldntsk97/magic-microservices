package com.rstkhldntsk.client.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {

}
