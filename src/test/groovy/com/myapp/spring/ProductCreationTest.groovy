package com.myapp.spring


import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

import com.fasterxml.jackson.databind.ObjectMapper
import com.myapp.spring.domain.Product
import com.myapp.spring.service.ProductService

import spock.lang.Specification

import static groovy.json.JsonOutput.toJson

import static org.springframework.http.MediaType.APPLICATION_JSON

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc

class ProductCreationTest extends Specification {

   @Autowired
   
    MockMvc mvc
   
   
   
   
    @SpringBean
   
   ProductService productService=Mock()
   
   
   
   
   @Autowired
   
   ObjectMapper objectMapper
   
   
   
   
   def "should pass product save details to domain component and return 'ok' status"() {
   
   given:
   
   Product request = new Product(
   
   productId : 4,
   
   productName : 'SamsungGalaxyNote',
   
   
   
                price: 78546.5,
   
                starRating: 4.2
   
   )
   
   and:
   
   productService.saveProduct(request)
   
       
   
        when:
   
        def response = mvc.perform(
   
                post('/api/products').contentType(APPLICATION_JSON).content(toJson(request))
   
        ).andReturn().response // notice the extra call to: andReturn()
   
   
   
   
        then:
   
        response.status == HttpStatus.OK.value()
   
   }
   
}