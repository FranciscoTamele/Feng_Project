%%
%class AnalisadorLexico
%unicode
%public

%{
  // Importação de pacotes necessários
  import java.io.IOException;
  import java.util.HashMap;
  import java_cup.runtime.Symbol;

%}

/* Definições das palavras-chave */
%{

      public interface ListenerTokens {
        void getTokens(String keyword, String tokens);
        void end();
        void error(Object object);
      }

      private ListenerTokens listenerTokens;
      public AnalisadorLexico(java.io.Reader in, ListenerTokens listenerTokens) {
        this.zzReader = in;
        this.listenerTokens = listenerTokens;
      }

  // Mapeamento de palavras-chave para seus respectivos tokens
  private static final HashMap<String, Integer> keywords = new HashMap<>();
  static {
    keywords.put("if", Tokens.IF);
    keywords.put("else", Tokens.ELSE);
    keywords.put("while", Tokens.WHILE);
    keywords.put("for", Tokens.FOR);
    keywords.put("return", Tokens.RETURN);
    // Adicione mais palavras-chave conforme necessário
  }
%}

%%

/* Padrões de tokens */
[0-9]+                  { this.listenerTokens.getTokens("NUMBER",yytext()); }
[a-zA-Z_][a-zA-Z0-9_]*  {
                          // Verifica se o texto corresponde a uma palavra-chave
                          Integer token = keywords.get(yytext());
                          if (token != null) {
                            this.listenerTokens.getTokens(yytext(),String.valueOf(token));
                          } else {
                            this.listenerTokens.getTokens("IDENTIFIER",yytext());
                          }
                        }
"+"                     { this.listenerTokens.getTokens("PLUS",yytext());}
"-"                     { this.listenerTokens.getTokens("MINUS",yytext()); }
"="                     { this.listenerTokens.getTokens("EQUAL",yytext()); }
";"                     { this.listenerTokens.getTokens("SEMI",yytext()); }
[\n\r]+                   { /* Ignorar novas linhas */ }
" "                     { /* Ignorar espaços em branco */ }
.                       { this.listenerTokens.getTokens("UNKNOWN",yytext()); }
