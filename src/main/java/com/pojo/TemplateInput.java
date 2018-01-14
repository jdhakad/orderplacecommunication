package com.pojo;

import com.service.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateInput {
    private List<Template> templateList;
    private Map<String, Object> templateCtx;
}
