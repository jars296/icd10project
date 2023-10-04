import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

public class Icd10 {
    public String getPreviousCode() {
        return previousCode;
    }

    public void setPreviousCode(String previousCode) {
        this.previousCode = previousCode;
    }

    private String previousCode;
    private String code;
    private String codeShortDescription;
    private String codeLongDescription;


    private Integer type;
    private String other_description;


    private String  code_relations;


    public void setOther_description(String other_description) {
        this.other_description = other_description;
    }

    public String getOther_description() {
        return other_description;
    }

    public Icd10(String previousCode, String code, String codeShortDescription, String codeLongDescription, Integer type, String other_description, String code_relations ) {
        this.previousCode = previousCode;
        this.code = code;
        this.codeShortDescription = codeShortDescription;
        this.codeLongDescription = codeLongDescription;
        this.type = type;

        this.code_relations= code_relations;
        this.other_description=other_description;
    }

    public String getCode() {
        return code;
    }

    public String getCodeShortDescription() {
        return codeShortDescription;
    }

    public String getCodeLongDescription() {
        return codeLongDescription;
    }

    public Integer getType() {
        return type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCodeShortDescription(String codeShortDescription) {
        this.codeShortDescription = codeShortDescription;
    }

    public void setCodeLongDescription(String codeLongDescription) {
        this.codeLongDescription = codeLongDescription;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getCode_relations() {
        return code_relations;
    }

    public void setCode_relations(String code_relations) {
        this.code_relations = code_relations;
    }



    static  public  Icd10 Chapter(String line){
        String[] parts = processLine((line));//line.split(",");
        String previousCode = "";
        String code = parts[0];
        String codeShortDescription = parts[4];
        String codeLongDescription = parts[4];

        return new Icd10(previousCode,code,codeShortDescription,codeLongDescription,0 , " " ," " );
    }


  static  private Icd10 Section(String line){
        String[] parts = processLine(line);//line.split(",");

        String previousCode = parts[0];
        String code = parts[6];
        String codeShortDescription = parts[10];
        String codeLongDescription = parts[10];

        return new Icd10(previousCode,code,codeShortDescription,codeLongDescription,1, " "," ");
    }

   static  public Icd10 Code ( String line){
        String[] parts =processLine((line));//line.split(",");
        String previousCode = parts[6];
        String code = parts[12];
        String codeShortDescription = parts[22];
        String codeLongDescription = parts[20];
    //   System.out.println(Arrays.stream(parts).toList().toString()+"------code");
        return new Icd10(previousCode,code,codeShortDescription,codeLongDescription, 2, " "," ");
    }


    @Override
    public String toString() {
        return "Icd10{" +
                "previousCode='" + previousCode + '\'' +
                ", code='" + code + '\'' +
                ", codeShortDescription='" + codeShortDescription + '\'' +
                ", codeLongDescription='" + codeLongDescription + '\'' +
                ", type=" + type +
                '}';
    }



    private static String[] processLine(String line) {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|([^,]*)");
        Matcher matcher = pattern.matcher(line);
        List<String> lookup = new ArrayList<>();

        int tokenIndex = 0;
        String[] tokens = new String[100]; // Assuming a maximum of 30 tokens in a line

        while (matcher.find()) {
            String token = matcher.group(1); // Check for content within double quotes
            if (token == null) {
                token = matcher.group(2); // If not within double quotes, get the default token
            }
            tokens[tokenIndex++] = token;

        }


        return tokens;
    }


    private static Icd10 solve(String tokens){


          return null;


    }
    public static void main(String[] args) {
        String line = "A00-B99,Certain infectious and parasitic diseases (A00-B99),Algumas doenças infecciosas e parasitárias,A00-A09,Intestinal infectious diseases,Doenças infecciosas intestinais,A047,0,Enterocolitis due to Clostridium difficile,Enterocolitis due to Clostridium difficile,Enterocolite devida a Clostridium difficile,Enterocolite C Clostridium difficile,2018,,,,,Novo,,,,,,,,,,,,,,,,,,43,36";
        String[] tokens = new String[100];
        List<String> codes = new ArrayList<>();
        String filePath = "tabelas.csv"; // Replace with the actual file path
        List<String> lines=new ArrayList<>();
        int n=0, n2=0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null&& n<8) {
               // if(line!=null)

                //System.out.println("===========================================================");
               String result;
               Icd10 c= Chapter(line);
               if(!codes.contains(Chapter(line).code+'c')){
                   codes.add(c.code+'c')  ;
                   n2++;
                   System.out.println( Chapter(line).toString()+"-----Capitulo"+ n++);
               }

                if(!codes.contains(result=Section(line).code)){
                    codes.add(result)  ;
                    n2++;
                    System.out.println( Section(line).toString()+"-----Section"+n++);
                }

               // if(!codes.contains(result=Code(line).code)){
                    codes.add(result)  ;
                    System.out.println( Code(line).toString()+"-----Code"+n++);
              //  }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }



System.out.println(n2);

    }

}
