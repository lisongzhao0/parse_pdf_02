package com.happy.gene.pdf.generate.model;

import com.happy.gene.pdf.generate.ICloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板组件
 * Created by zhaolisong on 25/07/2017.
 */
public class TemplateModel extends AbstractModel {

    public static TemplateModel newInstance() { return new TemplateModel(); }

    private String             selectedCondition;
    private List<ConditionModel> conditions = new ArrayList<>();

    public String selectedCondition() { return selectedCondition; }
    public List<ConditionModel> conditionsLink() { return conditions; }
    public List<ConditionModel> conditions() {
        List<ConditionModel> conditionCopy = new ArrayList<>();
        if (null==conditions) { return conditionCopy; }

        for (ConditionModel tmp : conditions) {
            conditionCopy.add((ConditionModel) tmp.clone(tmp.createBlank()));
        }
        return conditions;
    }
    public ConditionModel condition(String condition) {
        if (null==condition || "".equalsIgnoreCase(condition.trim())) { return null; }
        condition = condition.trim();
        if (null!=conditions && !conditions.isEmpty()) {
            for (ConditionModel con : conditions) {
                if (!condition.equals(con.condition())) { continue; }
                return (ConditionModel)con.clone(con.createBlank());
            }
        }
        return null;
    }
    public ConditionModel condition(int conditionIndex) {
        if (null!=conditions && !conditions.isEmpty()) {
            for (ConditionModel con : conditions) {
                if (conditionIndex != con.index()) { continue; }
                return (ConditionModel)con.clone(con.createBlank());
            }
        }
        return null;
    }

    public TemplateModel selectedCondition(String selectedCondition) { this.selectedCondition = selectedCondition; return this; }
    public TemplateModel conditions(List<ConditionModel> conditions) { this.conditions = conditions; return this; }
    public TemplateModel condition(ConditionModel condition) {
        if (null==condition) { return this; }
        if (null==this.conditions) { this.conditions = new ArrayList<>(); }
        this.conditions.add(condition);
        return this;
    }

    @Override public ICloneable createBlank() { return new TemplateModel(); }
    @Override public ICloneable clone(Object dest) {
        if (!(dest instanceof TemplateModel)) { return null; }
        super.clone(dest);

        ((TemplateModel) dest).conditions = null==conditions ? null : conditions();

        return (ICloneable) dest;
    }


    /**
     *  不同 condition 组件
     */
    public static class ConditionModel extends AbstractModel {

        public static ConditionModel newInstance() { return new ConditionModel(); }
        public static ConditionModel newInstance(String condition) { return new ConditionModel(condition); }

        private String            condition;
        private List<AbstractModel> components = new ArrayList<>();

        public ConditionModel() {}
        public ConditionModel(String condition) { condition(condition); }

        public String condition() { return this.condition; }
        public List<AbstractModel> componentsLink() { return components; }
        public List<AbstractModel> components() {
            List<AbstractModel> componentsCopy = new ArrayList<>();
            if (null==components) { return componentsCopy; }

            for (AbstractModel tmp : components) {
                componentsCopy.add((AbstractModel) tmp.clone(((ICloneable) tmp).createBlank()));
            }
            return components;
        }

        public ConditionModel condition(String condition) { this.condition = condition; return this; }
        public ConditionModel components(List<AbstractModel> components) { this.components = components; return this; }
        public ConditionModel component(AbstractModel component) {
            if (null==component)  { return this; }
            if (null==this.components) { this.components = new ArrayList<>(); }
            this.components.add(component);
            return this;
        }

        @Override public ICloneable createBlank() { return new ConditionModel(); }
        @Override public ICloneable clone(Object dest) {
            if (!(dest instanceof ConditionModel)) { return null; }
            super.clone(dest);

            ((ConditionModel) dest).condition  = null==condition ? null : condition;
            ((ConditionModel) dest).components = null==components? null : components();

            return (ICloneable) dest;
        }
    }
}
