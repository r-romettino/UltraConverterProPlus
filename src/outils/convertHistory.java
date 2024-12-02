package outils;

public class convertHistory {
	
	private float result;
	private String fromUnit;
	private String toUnit;
	private float value;
	private String Type;
	
	// Constructeur par défaut requis pour Jackson
	public convertHistory() {
	}
	
	public convertHistory (float result, String fromUnit, String toUnit, float value, String selectedType) {
		this.setResult(result);
		this.setFromUnit(fromUnit);
		this.setToUnit(toUnit);
		this.setValue(value);
		this.setType(selectedType);
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public String getFromUnit() {
		return fromUnit;
	}

	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}

	public String getToUnit() {
		return toUnit;
	}

	public void setToUnit(String toUnit) {
		this.toUnit = toUnit;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
    @Override
    public String toString() {
    	String resultText = String.format("%s : %.2f %s = %.2f %s", Type, value, fromUnit, result, toUnit);
    	return resultText;
    }
	
}
