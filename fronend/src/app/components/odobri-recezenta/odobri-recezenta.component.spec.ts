import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdobriRecezentaComponent } from './odobri-recezenta.component';

describe('OdobriRecezentaComponent', () => {
  let component: OdobriRecezentaComponent;
  let fixture: ComponentFixture<OdobriRecezentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdobriRecezentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdobriRecezentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
