/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author chris
 */
public class ProfileDataRepository implements ModelInterfaceDataRepository {
    private Integer id;
    private String nameProfile;
    private String domain;
    private String address;
    
    public ProfileDataRepository(Integer candidateId, String candidateNameProfile, 
            String candidateDomain, String candidateAddress)
    {
        this.id = candidateId;
        this.nameProfile = candidateNameProfile;
        this.domain = candidateDomain;
        this.address = candidateAddress;
    }
    
    protected void setId(Integer candidateId)
    {
        this.id = candidateId;
    }
    
    protected void setName(String candidateName) 
    {
        this.nameProfile = candidateName;
    }
    
    protected void setDomain(String candidateDomain)
    {
        this.domain = candidateDomain;
    }
    
    protected void setAddress(String candidateAddress)
    {
        this.address = candidateAddress;
    }
    
    public Integer getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.nameProfile;
    }
    
    public String getDomain()
    {
        return this.domain;
    }
    
    public String getAddress()
    {
        return this.address;
    }
}
