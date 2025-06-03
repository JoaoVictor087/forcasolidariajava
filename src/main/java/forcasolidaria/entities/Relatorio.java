package forcasolidaria.entities;

public class Relatorio {
    private int totalPendente;
    private int qntAlimentacaoPendente;
    private int qntMoradiaPendente;
    private int qntTransportePendente;
    private int qntOrientacaoPendente;
    private int qntEmergenciasMedicasPendente;
    private int qntDesastresNaturaisPendente;
    private int qntOutrosPendente;
    private int totalConcluido;
    private int qntAlimentacaoConcluido;
    private int qntMoradiaConcluido;
    private int qntTransporteConcluido;
    private int qntOrientacaoConcluido;
    private int qntEmergenciasMedicasConcluido;
    private int qntDesastresNaturaisConcluido;
    private int qntOutrosConcluido;

    public Relatorio() {
    }

    public Relatorio(int totalPendente, int qntOutrosConcluido, int qntDesastresNaturaisConcluido,
                     int qntEmergenciasMedicasConcluido, int qntOrientacaoConcluido, int qntTransporteConcluido,
                     int qntMoradiaConcluido, int qntAlimentacaoConcluido, int totalConcluido, int qntOutrosPendente,
                     int qntDesastresNaturaisPendente, int qntEmergenciasMedicasPendente, int qntOrientacaoPendente,
                     int qntTransportePendente, int qntMoradiaPendente, int qntAlimentacaoPendente) {
        this.totalPendente = totalPendente;
        this.qntOutrosConcluido = qntOutrosConcluido;
        this.qntDesastresNaturaisConcluido = qntDesastresNaturaisConcluido;
        this.qntEmergenciasMedicasConcluido = qntEmergenciasMedicasConcluido;
        this.qntOrientacaoConcluido = qntOrientacaoConcluido;
        this.qntTransporteConcluido = qntTransporteConcluido;
        this.qntMoradiaConcluido = qntMoradiaConcluido;
        this.qntAlimentacaoConcluido = qntAlimentacaoConcluido;
        this.totalConcluido = totalConcluido;
        this.qntOutrosPendente = qntOutrosPendente;
        this.qntDesastresNaturaisPendente = qntDesastresNaturaisPendente;
        this.qntEmergenciasMedicasPendente = qntEmergenciasMedicasPendente;
        this.qntOrientacaoPendente = qntOrientacaoPendente;
        this.qntTransportePendente = qntTransportePendente;
        this.qntMoradiaPendente = qntMoradiaPendente;
        this.qntAlimentacaoPendente = qntAlimentacaoPendente;
    }

    public int getTotalPendente() {
        return totalPendente;
    }

    public void setTotalPendente(int totalPendente) {
        this.totalPendente = totalPendente;
    }

    public int getQntOutrosConcluido() {
        return qntOutrosConcluido;
    }

    public void setQntOutrosConcluido(int qntOutrosConcluido) {
        this.qntOutrosConcluido = qntOutrosConcluido;
    }

    public int getQntDesastresNaturaisConcluido() {
        return qntDesastresNaturaisConcluido;
    }

    public void setQntDesastresNaturaisConcluido(int qntDesastresNaturaisConcluido) {
        this.qntDesastresNaturaisConcluido = qntDesastresNaturaisConcluido;
    }

    public int getQntEmergenciasMedicasConcluido() {
        return qntEmergenciasMedicasConcluido;
    }

    public void setQntEmergenciasMedicasConcluido(int qntEmergenciasMedicasConcluido) {
        this.qntEmergenciasMedicasConcluido = qntEmergenciasMedicasConcluido;
    }

    public int getQntOrientacaoConcluido() {
        return qntOrientacaoConcluido;
    }

    public void setQntOrientacaoConcluido(int qntOrientacaoConcluido) {
        this.qntOrientacaoConcluido = qntOrientacaoConcluido;
    }

    public int getQntTransporteConcluido() {
        return qntTransporteConcluido;
    }

    public void setQntTransporteConcluido(int qntTransporteConcluido) {
        this.qntTransporteConcluido = qntTransporteConcluido;
    }

    public int getQntMoradiaConcluido() {
        return qntMoradiaConcluido;
    }

    public void setQntMoradiaConcluido(int qntMoradiaConcluido) {
        this.qntMoradiaConcluido = qntMoradiaConcluido;
    }

    public int getQntAlimentacaoConcluido() {
        return qntAlimentacaoConcluido;
    }

    public void setQntAlimentacaoConcluido(int qntAlimentacaoConcluido) {
        this.qntAlimentacaoConcluido = qntAlimentacaoConcluido;
    }

    public int getTotalConcluido() {
        return totalConcluido;
    }

    public void setTotalConcluido(int totalConcluido) {
        this.totalConcluido = totalConcluido;
    }

    public int getQntOutrosPendente() {
        return qntOutrosPendente;
    }

    public void setQntOutrosPendente(int qntOutrosPendente) {
        this.qntOutrosPendente = qntOutrosPendente;
    }

    public int getQntDesastresNaturaisPendente() {
        return qntDesastresNaturaisPendente;
    }

    public void setQntDesastresNaturaisPendente(int qntDesastresNaturaisPendente) {
        this.qntDesastresNaturaisPendente = qntDesastresNaturaisPendente;
    }

    public int getQntEmergenciasMedicasPendente() {
        return qntEmergenciasMedicasPendente;
    }

    public void setQntEmergenciasMedicasPendente(int qntEmergenciasMedicasPendente) {
        this.qntEmergenciasMedicasPendente = qntEmergenciasMedicasPendente;
    }

    public int getQntOrientacaoPendente() {
        return qntOrientacaoPendente;
    }

    public void setQntOrientacaoPendente(int qntOrientacaoPendente) {
        this.qntOrientacaoPendente = qntOrientacaoPendente;
    }

    public int getQntTransportePendente() {
        return qntTransportePendente;
    }

    public void setQntTransportePendente(int qntTransportePendente) {
        this.qntTransportePendente = qntTransportePendente;
    }

    public int getQntMoradiaPendente() {
        return qntMoradiaPendente;
    }

    public void setQntMoradiaPendente(int qntMoradiaPendente) {
        this.qntMoradiaPendente = qntMoradiaPendente;
    }

    public int getQntAlimentacaoPendente() {
        return qntAlimentacaoPendente;
    }

    public void setQntAlimentacaoPendente(int qntAlimentacaoPendente) {
        this.qntAlimentacaoPendente = qntAlimentacaoPendente;
    }

}
