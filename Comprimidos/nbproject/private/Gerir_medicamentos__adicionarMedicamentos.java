while (true) {
            System.out.print("Introduza o código do medicamento: ");
            int id = Read.Int();

            for (i = 0; i < meds.size(); i++) {
                if (meds.get(i).getid() == id) {
                    System.out.println("Código já existente.");
                    break;
                } else {

                    System.out.print("Introduza a descrição do medicamento: ");
                    String descricao = Read.String();

                    System.out.print("Introduza a quantidade a tomar: ");
                    int quantidade = Read.Int();

                    System.out.print("Introduza a data da primeira toma no formato hh:mm, dd/mm/aa.\n");
                    System.out.print("Hora: ");
                    int horas = Read.Int();

                    System.out.print("Minuto: ");
                    int min = Read.Int();

                    System.out.print("Dia: ");
                    int dia = Read.Int();

                    System.out.print("Mês: ");
                    int mes = Read.Int();

                    System.out.print("Ano: ");
                    int ano = Read.Int();
                    
                    LocalDateTime prim_toma = LocalDateTime.of(ano, mes, dia, horas, min);
                    ArrayList<LocalDateTime> tomas_futuras = new ArrayList();

                    tomas_futuras.add(prim_toma);

                    System.out.print("Medicamento deve ser tomado de hora a hora ou de dias a dias(h/d): ");
                    char op = Read.Char();

                    while (((op != 'h') || (op
                            != 'd'))) {

                        switch (op) {

                            case 'h':
                                System.out.println("Tomar medicamento de quantas em quantas horas?");
                                int horasToma = Read.Int();

                                Medicamento m1 = new Medicamento(id, nome, descricao, horasToma, quantidade, tomas_futuras);
                                utilizadores.get(idUtilizador).getMedicamentos().add(m1);   // adiciona o medicamento à lista dos medicamentos

                                return;

                            case 'd':
                                System.out.println("Tomar medicamento de quantos em quantos dias?");
                                int diasToma = Read.Int();
                                int periodoTomaDias = 24 * diasToma;
                                System.out.println("O medicamento vai ser tomado de " + periodoTomaDias + " horas em " + periodoTomaDias + " horas.");

                                Medicamento m2 = new Medicamento(id, nome, descricao, periodoTomaDias, quantidade, tomas_futuras);
                                utilizadores.get(idUtilizador).getMedicamentos().add(m2);  // adiciona o medicamento à lista dos medicamentos

                                return;

                            default:
                                System.out.println("Opção Inválida");
                        }
                        break;
                    }

                    int m1_m2 = utilizadores.get(idUtilizador).getMedicamentos().size() - 1;          // descobre qual a posiçao do ultimo medicamento 
                    Medicamento med = utilizadores.get(idUtilizador).getMedicamentos().get(m1_m2);  // descobre qual o ultimo medicamento

                    for (i = 0; i < quantidade - 1; i++) {
                        int new_hora = prim_toma.getHora() + med.getPeriodo_toma();
                        int new_dia = 0;
                        int new_mes = 0;
                        int new_ano = 0;

                        while (new_hora > 24) {
                            int new_h = new_hora - 24;
                            new_dia++;

                            new_hora = new_h;
                        }

                        while (new_dia > 30) {
                            int new_d = new_dia - 30;
                            new_mes++;

                            new_dia = new_d;
                        }

                        while (new_mes > 12) {
                            int new_m = new_mes - 12;
                            new_ano++;

                            new_mes = new_m;
                        }

                        int new_minutos = prim_toma.getMinuto();

                        LocalDateTime new_toma =  LocalDateTime.of(new_ano, new_mes, new_dia, new_hora, new_minutos);
                        // minutos da primeira toma 

                        tomas_futuras.add(new_toma);

                        prim_toma = new_toma;
                    }

                    utilizadores.get(idUtilizador).getMedicamentos().get(m1_m2).setTomas_Futuras(tomas_futuras);

                    System.out.println(tomas_futuras);
                }
            }
        }