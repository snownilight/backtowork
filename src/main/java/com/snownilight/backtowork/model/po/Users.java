package com.snownilight.backtowork.model.po;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    
    private Long id;
    private String name;
    private String email;
}
