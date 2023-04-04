package com.clearlife.toppinginjector;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToppingDto implements Serializable {
    private String useremail;
    private Set<String> toppingNames;
}
