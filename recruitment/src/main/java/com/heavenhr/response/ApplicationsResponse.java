package com.heavenhr.response;

import java.util.ArrayList;
import java.util.List;

import com.heavenhr.dto.ApplicationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationsResponse {

    private List<ApplicationDto> applications = new ArrayList<>();
}
