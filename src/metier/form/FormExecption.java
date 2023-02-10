package metier.form;

public class FormExecption extends Exception{
    private String formName,fieldName;
    String consigne;
    public FormExecption(){super("Erreur dans le formulaire !");}
    public FormExecption(String errorMsg){super(errorMsg);}
    public FormExecption(String errorMsg,String cons){super(errorMsg);this.consigne=cons;}


    public FormExecption(String formName,String fieldName,String errorMsg,String cons){
        super(errorMsg);
        this.fieldName=fieldName;
        this.formName=formName;
        this.consigne=cons;
    }

    public FormExecption(String formName,String fieldName,String cons){
        this();
        this.fieldName=fieldName;
        this.formName=formName;
        this.consigne=cons;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


}

