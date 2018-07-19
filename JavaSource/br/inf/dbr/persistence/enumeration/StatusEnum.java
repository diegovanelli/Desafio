package br.inf.dbr.persistence.enumeration;

public enum StatusEnum  {
    
    NOVO("Novo",0),
    ANDAMENTO("Em andamento",1),
	CONCLUIDO("Concluído",2);

    String label;
    int value;
    
    private StatusEnum(String label, int value) {
        this.label = label;
        this.value = value;
    }
    
    public String getHint() {
        return null;
    }

    public String getKey() {
        return this.name();
    }

    public String getLabel() {
        return this.label;
    }
    
    public int getValue() {
        return value;
    }
    
    public static StatusEnum getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException("Argumento 'value' é obrigatório.");
        if (value.equals(StatusEnum.NOVO.getValue())) {
            return StatusEnum.NOVO;
        } else if (value.equals(StatusEnum.ANDAMENTO.getValue())) {
            return StatusEnum.ANDAMENTO;
        } else if (value.equals(StatusEnum.CONCLUIDO.getValue())) {
            return StatusEnum.CONCLUIDO;
        }
        return null;
    }
    
}
