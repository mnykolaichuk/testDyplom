package com.mnykolaichuk.luv2code.springboot.thymeleafdemo.model.email;

import com.mnykolaichuk.luv2code.springboot.thymeleafdemo.model.entity.OrderAnswer;

public class CreateOrderEmailContext extends AbstractEmailContext {

    private String token;


    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        OrderAnswer orderAnswer = (OrderAnswer) context; // we pass the customer informati
        put("orderAnswer", orderAnswer);
        setTo(orderAnswer.getWorkshop().getEmail());

        setTemplateLocation("email/order-created-information");
        setSubject("New order");
        setFrom("no-reply@javadevjournal.com");
    }

    public void setInformationUrl(final String showCreatedOrderListUrl){
        put("showCreatedOrderListUrl", showCreatedOrderListUrl);
    }

}