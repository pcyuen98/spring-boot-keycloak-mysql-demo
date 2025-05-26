package com.demo.httpsession.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryDTO {

    private Long id;
    private Date loginDate;

    private UserDTO userDTO;
}
