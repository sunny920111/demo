package com.demo.common.file.entity;

import com.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TN_CM_FILE")
@Getter
@Setter
@DynamicUpdate
public class AttachFile extends BaseEntity {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "fileId", columnDefinition = "VARCHAR(35)")
  private String fileId;

  private String fileName;
  private String fileSize;
  private String ownerObjectPkId;
  private String fileExtensionName;
  private String fileMimeTypeName;
  private String deleteYn = "N";
  private int orderIndex;
}
